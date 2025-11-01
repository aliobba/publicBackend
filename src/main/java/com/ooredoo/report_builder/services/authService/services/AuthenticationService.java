package com.ooredoo.report_builder.services.authService.services;

import com.ooredoo.report_builder.controller.auth.AuthenticationRequest;
import com.ooredoo.report_builder.controller.auth.AuthenticationResponse;
import com.ooredoo.report_builder.controller.auth.RegistrationRequest;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.repo.TokenRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.security.JwtService;
import com.ooredoo.report_builder.services.services.email.EmailService;
import com.ooredoo.report_builder.services.services.email.EmailTemplateName;
import com.ooredoo.report_builder.user.Token;
import com.ooredoo.report_builder.user.User;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public static final String ACTIVATION_URL = "http://localhost:4200/activation-account";

    public AuthenticationService(
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            TokenRepository tokenRepository,
            EmailService emailService, JwtService jwtService,
            AuthenticationManager authenticationManager) {

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public void register(RegistrationRequest request) throws MessagingException, IOException {

        var userRole = roleRepository.findByName("MAIN_ADMIN")
                .orElseThrow(() -> new IllegalStateException("Role user not initialized"));


        var user = User.builder()
                .first_Name(request.getFirstname())
                .last_Name(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .userType(request.getUserType())
                .pin(request.getPinHash())
                .role(userRole)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException, IOException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendWithSendGrid(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                ACTIVATION_URL,
                newToken,
                "OTP"

        );

    }

    private String generateAndSaveActivationToken(User user) {
        //generate a Token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken).createdAt(LocalDateTime.now())
                .expirationAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws MessagingException, IOException {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String,Object>();
        var user = ((User)auth.getPrincipal());
        claims.put("fullname", user.fullName());
        var jwtToken = jwtService.generateToken(claims,user);
        
        // Send OTP email after successful authentication
        sendOtpEmail(user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
    @Transactional
    public void activateAccount(String token) throws MessagingException, IOException {

        Token savedToken = tokenRepository.findByToken(token)
                //NEED TO HANDLE THE EXCEPTION
                .orElseThrow(()-> new RuntimeException("INVALID TOKEN"));
        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Token expired. New Token is send to the same email");
        }
        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    @Transactional
    public void verifyActivationCode(String code) {
        Token savedToken = tokenRepository.findByToken(code)
                .orElseThrow(() -> new RuntimeException("Invalid activation code"));
        
        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())) {
            throw new RuntimeException("Activation code expired");
        }
        
        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Check if user has a password set
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            // User needs password setup - don't activate yet
            // The frontend will handle showing password setup form
            throw new RuntimeException("PASSWORD_SETUP_REQUIRED");
        }
        
        // User has password - activate account
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public void resendActivationEmail(String email) throws MessagingException, IOException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        if (user.isEnabled()) {
            throw new RuntimeException("Account is already activated");
        }
        
        sendValidationEmail(user);
    }

    private void sendOtpEmail(User user) throws MessagingException, IOException {
        var otpCode = generateAndSaveOtpToken(user);
        
        emailService.sendWithSendGrid(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.OTP_VERIFICATION,
                ACTIVATION_URL,
                otpCode,
                "OTP"
        );
    }

    private String generateAndSaveOtpToken(User user) {
        String generatedOtp = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedOtp)
                .createdAt(LocalDateTime.now())
                .expirationAt(LocalDateTime.now().plusMinutes(10)) // OTP expires in 10 minutes
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedOtp;
    }

    @Transactional
    public void verifyOtp(String otpCode) {
        Token savedToken = tokenRepository.findByToken(otpCode)
                .orElseThrow(() -> new RuntimeException("Invalid OTP code"));
        
        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())) {
            throw new RuntimeException("OTP code expired");
        }
        
        // Mark OTP as used
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public void resendOtp(String email) throws MessagingException, IOException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        sendOtpEmail(user);
    }

    @Transactional
    public void setupPassword(String activationCode, String password) {
        // Verify the activation code
        Token savedToken = tokenRepository.findByToken(activationCode)
                .orElseThrow(() -> new RuntimeException("Invalid activation code"));
        
        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())) {
            throw new RuntimeException("Activation code expired");
        }
        
        // Get the user
        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Set the password and activate the account
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        userRepository.save(user);
        
        // Mark token as used
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }
}

