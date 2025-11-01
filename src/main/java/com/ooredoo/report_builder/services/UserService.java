package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.dto.PinVerificationResponse;
import com.ooredoo.report_builder.enums.RoleType;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.*;
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

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService {

    public static final String ACTIVATION_URL = "http://localhost:4200/activate-account";
    public static final String PASSWORD_SETUP_URL = "http://localhost:4200/password-setup";
    @Autowired
    private final EnterpriseRepository enterpriseRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final TokenRepository tokenRepository;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private POSRepository posRepository;

    public UserService(EnterpriseRepository enterpriseRepository, RoleRepository roleRepository, TokenRepository tokenRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.enterpriseRepository = enterpriseRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllFromRegion(Integer regionId) {
        return userRepository.findAllUsersInRegionFull(regionId);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) throws MessagingException, IOException {
        if (user.getId() == null) {
            return createUser(user);
        } else {
            return updateUser(user);
        }
    }

    public void updatePassword(Integer userId, String newPassword) {
        Optional<User> userOpt = findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

    public User createUser(User user) throws MessagingException, IOException {
        // Validate required fields
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getFirst_Name() == null || user.getFirst_Name().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (user.getLast_Name() == null || user.getLast_Name().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        
        var userRole = roleRepository.findByName("SIMPLE_USER")
                .orElseThrow(() -> new IllegalStateException("Role user not initialized"));
        
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        
        var newUser = User.builder()
                .first_Name(user.getFirst_Name())
                .last_Name(user.getLast_Name())
                .email(user.getEmail())
                .password(user.getPassword() != null && !user.getPassword().trim().isEmpty() 
                        ? passwordEncoder.encode(user.getPassword()) 
                        : null) // No password for admin-created users
                .pin(user.getPin())
                .birthdate(user.getBirthdate())
                .userType(user.getUserType() == null 
                        ? UserType.INTERNAL
                        : user.getUserType())
                .enabled(false) // User needs activation
                .accountLocked(false)
                .build();
        
        // Handle POS assignment if provided
        if (user.getPos_Code() != null && !user.getPos_Code().trim().isEmpty()) {
            POS pos = posRepository.findByCodeOfPOS(user.getPos_Code())
                    .orElseThrow(() -> new ResourceNotFoundException("POS not found with code: " + user.getPos_Code()));

            newUser.setPos_Code(user.getPos_Code());
            newUser.setPos(pos);
            
            // Validate role for POS users
            if (user.getUserType() != null && user.getUserType().equals(UserType.POS)) {
                if (user.getRole() != null) {
                    // Fetch full role entity if only ID is provided
                    Role roleToValidate = user.getRole();
                    if (roleToValidate.getId() != null && (roleToValidate.getName() == null || roleToValidate.getName().trim().isEmpty())) {
                        Integer roleId = roleToValidate.getId();
                        roleToValidate = roleRepository.findById(roleId)
                                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
                    }
                    
                    // Check if user has valid POS role
                    if (roleToValidate.getName() != null) {
                        boolean hasValidPOSRole = roleToValidate.getName().equals(RoleType.HEAD_OF_POS.getValue()) 
                                        || roleToValidate.getName().equals(RoleType.COMMERCIAL_POS.getValue());
                        
                        if (!hasValidPOSRole) {
                            throw new IllegalArgumentException("POS users must have HEAD_OF_POS or COMMERCIAL_POS role");
                        }
                        newUser.setRole(roleToValidate);
                    }
                }
            }
        }
        
        // Set role - use provided role or default to SIMPLE_USER (skip if already set for POS users)
        if (newUser.getRole() == null) {
            if (user.getRole() != null) {
                // If role has only ID, fetch the full role entity
                if (user.getRole().getId() != null && (user.getRole().getName() == null || user.getRole().getName().trim().isEmpty())) {
                    Role fullRole = roleRepository.findById(user.getRole().getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + user.getRole().getId()));
                    newUser.setRole(fullRole);
                } else if (user.getRole().getName() != null && !user.getRole().getName().trim().isEmpty()) {
                    // If role has name, try to find by name or use the provided role
                    Role roleByName = roleRepository.findByName(user.getRole().getName()).orElse(null);
                    newUser.setRole(roleByName != null ? roleByName : user.getRole());
                } else {
                    newUser.setRole(userRole);
                }
            } else {
                newUser.setRole(userRole);
            }
        }
        
        // Handle enterprise assignment if provided
        if (user.getEnterprise() != null) {
            Enterprise enterprise = enterpriseRepository.findById(user.getEnterprise().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found with id: " + user.getEnterprise().getId()));
            newUser.setEnterprise(enterprise);
        }
        
        userRepository.save(newUser);
        log.info("User created successfully with email: {}", newUser.getEmail());
        
        // Send password setup invitation if no password was provided
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            sendPasswordSetupInvitation(newUser);
        }
        
        return newUser;
    }

    public void deleteById(Integer id) {
        validateUserDeletion(id);
        
        // Delete all tokens associated with this user first
        List<Token> userTokens = tokenRepository.findAll().stream()
                .filter(token -> token.getUser() != null && token.getUser().getId().equals(id))
                .collect(Collectors.toList());
        
        if (!userTokens.isEmpty()) {
            tokenRepository.deleteAll(userTokens);
            log.info("Deleted {} tokens for user ID: {}", userTokens.size(), id);
        }
        
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }

    public Optional<User> existsByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByUserType(UserType userType) {
        return userRepository.findByUserType(userType);
    }

    public List<User> findAvailableHeads(UserType userType) {
        return userRepository.findAvailableHeads(userType);
    }

    public List<User> findAvailableHeadsByRole(String roleName) {
        return userRepository.findAvailableHeadsByRole(roleName);
    }

    public boolean verifyPin(String email, String pin) {
        Optional<User> user = findByEmail(email);
        if (user.isPresent() && user.get().getPin() != null) {
            return user.get().getPin().equals(pin);
        }
        return false;
    }

    public PinVerificationResponse verifyPinWithEnterprise(String email, String pin) {
        Optional<User> user = findByEmail(email);
        
        if (user.isPresent() && user.get().getPin() != null && user.get().getPin().equals(pin)) {
            User foundUser = user.get();
            
            PinVerificationResponse.EnterpriseThemeData enterpriseTheme = null;
            
            // Check if user has an enterprise assigned
            if (foundUser.getEnterprise() != null) {
                enterpriseTheme = PinVerificationResponse.EnterpriseThemeData.builder()
                        .enterpriseId(foundUser.getEnterprise().getId())
                        .enterpriseName(foundUser.getEnterprise().getEnterpriseName())
                        .primaryColor(foundUser.getEnterprise().getPrimaryColor())
                        .secondaryColor(foundUser.getEnterprise().getSecondaryColor())
                        .logoUrl(foundUser.getEnterprise().getLogoUrl())
                        .build();
            }
            
            return PinVerificationResponse.builder()
                    .valid(true)
                    .message("PIN verified successfully")
                    .enterpriseTheme(enterpriseTheme)
                    .build();
        }
        
        return PinVerificationResponse.builder()
                .valid(false)
                .message("Invalid PIN")
                .enterpriseTheme(null)
                .build();
    }

    public void updatePin(Integer userId, String newPin) {
        Optional<User> user = findById(userId);
        if (user.isPresent()) {
            user.get().setPin((newPin));
            userRepository.save(user.get());
        }
    }

    private User updateUser(User user) {
        Optional<User> existingUserOpt = findById(user.getId());
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update basic fields
            existingUser.setFirst_Name(user.getFirst_Name());
            existingUser.setLast_Name(user.getLast_Name());
            existingUser.setEmail(user.getEmail());
            existingUser.setBirthdate(user.getBirthdate());
            existingUser.setUserType(user.getUserType());
            existingUser.setEnabled(user.isEnabled());
            existingUser.setAccountLocked(user.isAccountLocked());

            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }
            if (user.getEnterprise() != null) {
                existingUser.setEnterprise(user.getEnterprise());
            }

            // Only update password if provided (not null and not empty)
            if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            // Only update PIN if provided (not null and not empty)
            if (user.getPin() != null && !user.getPin().trim().isEmpty()) {
                existingUser.setPin(user.getPin());
            }
            if (user.getPos_Code() != null && !user.getPos_Code().trim().isEmpty()) {
                // Check if POS code changed
                if (!user.getPos_Code().equals(existingUser.getPos_Code())) {
                    POS pos = posRepository.findByCodeOfPOS(user.getPos_Code())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "POS not found with code: " + user.getPos_Code()));

                    existingUser.setPos_Code(user.getPos_Code());
                    existingUser.setPos(pos);
                }
            }
            return userRepository.save(existingUser);
        }
        throw new IllegalArgumentException("User not found with id: " + user.getId());
    }

    private void validateUserDeletion(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Check if user is assigned as head of any organizational unit
        if (user.getRole() != null) {
            Role role = user.getRole();
            if (role.getName().equals(RoleType.HEAD_OF_SECTOR.getValue())) {
                throw new IllegalStateException("Cannot delete user: assigned as head of sector. Reassign sector first.");
            }
            if (role.getName().equals(RoleType.HEAD_OF_ZONE.getValue())) {
                throw new IllegalStateException("Cannot delete user: assigned as head of zone. Reassign zone first.");
            }
            if (role.getName().equals(RoleType.HEAD_OF_REGION.getValue())) {
                throw new IllegalStateException("Cannot delete user: assigned as head of region. Reassign region first.");
            }
            if (role.getName().equals(RoleType.HEAD_OF_POS.getValue())) {
                throw new IllegalStateException("Cannot delete user: assigned as head of POS. Reassign POS first.");
            }
        }
    }

    public User assignRoleToUser(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        // Set the role
        user.setRole(role);
        return userRepository.save(user);
    }

    public User assignRolesToUser(Integer userId, List<Integer> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalArgumentException("At least one role ID must be provided");
        }
        
        // Since user can only have one role, use the first role ID
        Integer roleId = roleIds.get(0);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        user.setRole(role);
        return userRepository.save(user);
    }

    public User removeRoleFromUser(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        // Remove role only if it matches the current role
        if (user.getRole() != null && user.getRole().getId().equals(roleId)) {
            user.setRole(null);
        }

        return userRepository.save(user);
    }

    // Method 5: Remove multiple roles from user (deprecated - users now have single role)
    public User removeRolesFromUser(Integer userId, List<Integer> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Since user can only have one role, remove if the current role is in the list
        if (user.getRole() != null && roleIds.contains(user.getRole().getId())) {
            user.setRole(null);
        }

        return userRepository.save(user);
    }

    // Method 6: Remove all roles from user
    public User removeAllRolesFromUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setRole(null);
        return userRepository.save(user);
    }

/*
    // Create User
    public UserResponseDTO createUser(UserRequest request) throws MessagingException {
        User user = userMapper.toEntity(request);
        user.setEnabled(false); // needs activation
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        sendValidationEmail(user);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Integer userId, UserRequest updateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (updateDTO.getFirstname() != null) user.setFirstname(updateDTO.getFirstname());
        if (updateDTO.getLastname() != null) user.setLastname(updateDTO.getLastname());
        if (updateDTO.getEmail() != null) user.setEmail(updateDTO.getEmail());

        return userMapper.toDto(userRepository.save(user));
    }*/

    // Delete User
    public void deleteUser(Integer id) {
        deleteById(id); // Use the updated deleteById method
    }

    /*
        // Get User
        public UserResponseDTO getUser(Integer id) {
            return userRepository.findById(id)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        // Get All
        public List<UserResponseDTO> getAllUsers() {
            return userRepository.findAll().stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        }
    */
    // Assign Role
    public void assignRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(roleId).orElseThrow();
        user.setRole(role);
        userRepository.save(user);
    }

    // Unassign Role
    public void unassignRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user.getRole() != null && user.getRole().getId().equals(roleId)) {
            user.setRole(null);
        }
        userRepository.save(user);
    }

    // ✅ Assign User to Enterprise
    @Transactional
    public void assignUserToEnterprise(Integer userId, Integer enterpriseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        if (enterprise.getUsersInEnterprise().contains(user)) {
            throw new ResourceNotFoundException("user already assigned to Enterprise");
        }
        enterprise.getUsersInEnterprise().add(user);
        enterpriseRepository.save(enterprise);
    }

    // ✅ Unassign User from Enterprise
    @Transactional
    public void unassignUserFromEnterprise(Integer userId, Integer enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Remove user from enterprise's user list
        enterprise.getUsersInEnterprise().removeIf(u -> u.getId().equals(userId));
        enterpriseRepository.save(enterprise);
        
        // Clear user's enterprise reference
        user.setEnterprise(null);
        userRepository.save(user);
    }

    @Transactional
    public List<User> getUsersByPOS(Integer posId) {
        return userRepository.findByPosId(posId);
    }

    @Transactional
    public User linkUserToPOS(Integer userId, String posCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (posCode == null || posCode.trim().isEmpty()) {
            throw new IllegalArgumentException("POS code cannot be empty");
        }

        POS pos = posRepository.findByCodeOfPOS(posCode)
                .orElseThrow(() -> new ResourceNotFoundException("POS not found with code: " + posCode));

        user.setPos_Code(posCode);
        user.setPos(pos);

        return userRepository.save(user);
    }

    @Transactional
    public User unlinkUserFromPOS(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Check if user is head of POS
        if (user.getPos() != null && user.getPos().getHeadOfPOS() != null
                && user.getPos().getHeadOfPOS().getId().equals(userId)) {
            throw new IllegalStateException("Cannot unlink user who is head of POS. Reassign POS head first.");
        }

        user.setPos_Code(null);
        user.setPos(null);

        return userRepository.save(user);
    }

    private void sendValidationEmail(User user) throws MessagingException, IOException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendWithSendGrid(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                ACTIVATION_URL,
                newToken,
                "Account Activation"
        );
    }

    private void sendPasswordSetupInvitation(User user) throws MessagingException, IOException {
        var newToken = generateAndSaveActivationToken(user);
        String passwordSetupUrlWithCode = PASSWORD_SETUP_URL + "?code=" + newToken + "&email=" + user.getEmail();

        emailService.sendWithSendGrid(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.PASSWORD_SETUP_INVITATION,
                passwordSetupUrlWithCode,
                newToken,
                "Complete Your Account Setup - Password Required"
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
    public void activateAccount(String token) throws MessagingException, IOException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (LocalDateTime.now().isAfter(savedToken.getExpirationAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("OPT expired. New OTP has been sent to your email.");
        }

        User user = userRepository.findById(savedToken.getUser().getId())
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

    @Transactional()
    public Optional<POS> getPOSByCode(String posCode) {
        return posRepository.findByCodeOfPOS(posCode);
    }

    @Transactional()
    public boolean posCodeExists(String posCode) {
        return posRepository.existsByCodeOfPOS(posCode);
    }

    // Search users by query (name, email, etc.)
    public List<User> searchUsers(String query) {
        if (query == null || query.trim().isEmpty()) {
            return findAll();
        }
        return userRepository.searchUsers(query.trim());
    }

    // Bulk delete users
    @Transactional
    public void bulkDeleteUsers(List<Integer> userIds) {
        for (Integer userId : userIds) {
            validateUserDeletion(userId);
        }
        
        // Delete all tokens associated with these users first
        List<Token> userTokens = tokenRepository.findAll().stream()
                .filter(token -> token.getUser() != null && userIds.contains(token.getUser().getId()))
                .collect(Collectors.toList());
        
        if (!userTokens.isEmpty()) {
            tokenRepository.deleteAll(userTokens);
            log.info("Deleted {} tokens for bulk user deletion", userTokens.size());
        }
        
        userRepository.deleteAllById(userIds);
        log.info("Bulk deleted {} users successfully", userIds.size());
    }

    // Bulk update user status
    @Transactional
    public void bulkUpdateUserStatus(List<Integer> userIds, Boolean enabled) {
        List<User> users = userRepository.findAllById(userIds);
        for (User user : users) {
            user.setEnabled(enabled);
        }
        userRepository.saveAll(users);
    }
    
    // End of UserService class
}
