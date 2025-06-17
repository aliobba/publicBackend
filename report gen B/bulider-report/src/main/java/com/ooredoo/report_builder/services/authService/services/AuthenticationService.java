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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public static final String ACTIVATION_URL = "//localhost:4200/activation-account";




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


    public void register(RegistrationRequest request) throws MessagingException {

        var userRole = roleRepository.findByName("MAIN_ADMIN")
                .orElseThrow(() -> new IllegalStateException("Role user not initialized"));

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                ACTIVATION_URL,
                newToken,
                "Acctivation account"

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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

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

        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
    @Transactional
    public void activateAccount(String token) throws MessagingException {

        Token savedToken = tokenRepository.findByToken(token)
                //NEED TO HANDLE THE EXCEPTION
                .orElseThrow(()-> new RuntimeException("INVALID TOKEN"));
        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Token expired. New Token is send to the same email");
        }
        var user = userRepository.findById(savedToken.getUser().getId_user())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }
}

