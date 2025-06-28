package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.controller.user.UserCreateRequest;
import com.ooredoo.report_builder.controller.user.UserResponse;
import com.ooredoo.report_builder.controller.user.UserUpdateRequest;
import com.ooredoo.report_builder.entity.*;
import com.ooredoo.report_builder.mapper.UserMapper;
import com.ooredoo.report_builder.repo.*;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.repo.TokenRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.services.services.email.EmailService;
import com.ooredoo.report_builder.services.services.email.EmailTemplateName;
import com.ooredoo.report_builder.user.Role;
import com.ooredoo.report_builder.user.Token;
import com.ooredoo.report_builder.user.User;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final SectorRepository sectorRepository;
    private final ZoneRepository zoneRepository;
    private final RegionRepository regionRepository;
    private final POSRepository posRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public static final String ACTIVATION_URL = "//localhost:4200/activation-account";

    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository, 
                     EnterpriseRepository enterpriseRepository, SectorRepository sectorRepository, 
                     ZoneRepository zoneRepository, RegionRepository regionRepository, 
                     POSRepository posRepository, RoleRepository roleRepository, 
                     TokenRepository tokenRepository, EmailService emailService, 
                     PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.sectorRepository = sectorRepository;
        this.zoneRepository = zoneRepository;
        this.regionRepository = regionRepository;
        this.posRepository = posRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    public UserResponse createUser(UserCreateRequest request) throws MessagingException {
        // Create new user entity directly from request
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setEnabled(false);
        user.setAccountLocked(false);

        // Set encoded password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Set enterprise if provided
        if (request.getEnterpriseId() != null) {
            Enterprise enterprise = enterpriseRepository.findById(request.getEnterpriseId())
                    .orElseThrow(() -> new RuntimeException("Enterprise not found"));
            user.setEnterprise(enterprise);
        }
        
        // Set sector if provided
        if (request.getSectorId() != null) {
            Sector sector = sectorRepository.findById(request.getSectorId())
                    .orElseThrow(() -> new RuntimeException("Sector not found"));
            user.setSector(sector);
        }
        
        // Set zone if provided
        if (request.getZoneId() != null) {
            Zone zone = zoneRepository.findById(request.getZoneId())
                    .orElseThrow(() -> new RuntimeException("Zone not found"));
            user.setZone(zone);
        }
        
        // Set region if provided
        if (request.getRegionId() != null) {
            Region region = regionRepository.findById(request.getRegionId())
                    .orElseThrow(() -> new RuntimeException("Region not found"));
            user.setRegion(region);
        }
        
        // Set POS if provided
        if (request.getPosId() != null) {
            POS pos = posRepository.findById(request.getPosId())
                    .orElseThrow(() -> new RuntimeException("POS not found"));
            user.setPos(pos);
        }
        
        // Set user type
        user.setUserType(request.getUserType());

        // Set roles
        List<Role> roles = request.getRoles().stream()
                .map(roleName -> roleRepository.findByName("ROLE_" + roleName.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toList());
        user.setRoles(roles);

        // Save user first (needed before sending email)
        user = userRepository.save(user);

        // Send Activation Email
        sendValidationEmail(user);

        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }
    
    public List<UserResponse> getUsersByEnterprise(int enterpriseId) {
        return userMapper.toResponseList(userRepository.findByEnterpriseId(enterpriseId));
    }
    
    public List<UserResponse> getUsersBySector(int sectorId) {
        return userMapper.toResponseList(userRepository.findBySectorId(sectorId));
    }
    
    public List<UserResponse> getUsersByZone(int zoneId) {
        return userMapper.toResponseList(userRepository.findByZoneId(zoneId));
    }
    
    public List<UserResponse> getUsersByRegion(int regionId) {
        return userMapper.toResponseList(userRepository.findByRegionId(regionId));
    }
    
    public List<UserResponse> getUsersByPOS(int posId) {
        return userMapper.toResponseList(userRepository.findByPosId(posId));
    }
    
    public List<UserResponse> getUsersByUserType(UserType userType) {
        return userMapper.toResponseList(userRepository.findByUserType(userType));
    }

    public UserResponse updateUser(Integer userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getFirstname() != null) user.setFirstname(request.getFirstname());
        if (request.getLastname() != null) user.setLastname(request.getLastname());
        
        // Update enterprise if provided
        if (request.getEnterpriseId() != null) {
            Enterprise enterprise = enterpriseRepository.findById(request.getEnterpriseId())
                    .orElseThrow(() -> new RuntimeException("Enterprise not found"));
            user.setEnterprise(enterprise);
        }
        
        // Update sector if provided
        if (request.getSectorId() != null) {
            Sector sector = sectorRepository.findById(request.getSectorId())
                    .orElseThrow(() -> new RuntimeException("Sector not found"));
            user.setSector(sector);
        }
        
        // Update zone if provided
        if (request.getZoneId() != null) {
            Zone zone = zoneRepository.findById(request.getZoneId())
                    .orElseThrow(() -> new RuntimeException("Zone not found"));
            user.setZone(zone);
        }
        
        // Update region if provided
        if (request.getRegionId() != null) {
            Region region = regionRepository.findById(request.getRegionId())
                    .orElseThrow(() -> new RuntimeException("Region not found"));
            user.setRegion(region);
        }
        
        // Update POS if provided
        if (request.getPosId() != null) {
            POS pos = posRepository.findById(request.getPosId())
                    .orElseThrow(() -> new RuntimeException("POS not found"));
            user.setPos(pos);
        }
        
        // Update user type if provided
        if (request.getUserType() != null) {
            user.setUserType(request.getUserType());
        }

        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                ACTIVATION_URL,
                newToken,
                "Account Activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
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

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Token expired. New token has been sent to your email.");
        }

        User user = userRepository.findById(savedToken.getUser().getId_user())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public User getCurrentAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
