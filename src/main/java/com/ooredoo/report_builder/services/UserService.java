package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.*;
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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {

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
    //private final UserMapper userMapper;
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


    public static final String ACTIVATION_URL = "//localhost:4200/activation-account";

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

    public User save(User user) throws MessagingException {
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

    public User createUser(User user) throws MessagingException {

        var newUser = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .pinHash(passwordEncoder.encode(user.getPinHash()))
                .dateOfBirth(user.getDateOfBirth())
                .userType(user.getUserType() == null || user.getUserType().toString().isEmpty()
                        ? UserType.SIMPLE_USER
                        : user.getUserType())
                .build();
        POS pos = posRepository.findByCodePOS(user.getcode_POS())
                .orElseThrow(() -> new ResourceNotFoundException("pos not found"));
        if(newUser.getUserType().getValue().contains("POS") && user.getcode_POS().equals(pos.getCodePOS())){
           newUser.setcode_POS(user.getcode_POS());
           newUser.setPos(pos);
        }
        userRepository.save(newUser);
        return newUser;
    }

    public void deleteById(Integer id) {
        validateUserDeletion(id);
        userRepository.deleteById(id);
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

    public boolean verifyPin(String email, String pin) {
        Optional<User> user = findByEmail(email);
        if (user.isPresent() && user.get().getPinHash() != null) {
            return passwordEncoder.matches(pin, user.get().getPinHash());
        }
        return false;
    }

    public void updatePin(Integer userId, String newPin) {
        Optional<User> user = findById(userId);
        if (user.isPresent()) {
            user.get().setPinHash(passwordEncoder.encode(newPin));
            userRepository.save(user.get());
        }
    }
    private User updateUser(User user) {
        Optional<User> existingUserOpt = findById(user.getId());
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update basic fields
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setEmail(user.getEmail());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setUserType(user.getUserType());
            existingUser.setEnabled(user.isEnabled());
            existingUser.setAccountLocked(user.isAccountLocked());

            // Update relationships if provided
            if (user.getRoles() != null) {
                existingUser.setRoles(user.getRoles());
            }
            if (user.getEnterprise() != null) {
                existingUser.setEnterprise(user.getEnterprise());
            }

            // Only update password if provided (not null and not empty)
            if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            // Only update PIN if provided (not null and not empty)
            if (user.getPinHash() != null && !user.getPinHash().trim().isEmpty()) {
                existingUser.setPinHash(passwordEncoder.encode(user.getPinHash()));
            }

            return userRepository.save(existingUser);
        }
        throw new IllegalArgumentException("User not found with id: " + user.getId());
    }

    private void validateUserDeletion(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Check if user is assigned as head of any organizational unit
        if (user.getUserType().equals(UserType.HEAD_OF_SECTOR)) {
            throw new IllegalStateException("Cannot delete user: assigned as head of sector. Reassign sector first.");
        }
        if (user.getUserType().equals(UserType.HEAD_OF_ZONE)) {
            throw new IllegalStateException("Cannot delete user: assigned as head of zone. Reassign zone first.");
        }
        if (user.getUserType().equals(UserType.HEAD_OF_REGION)) {
            throw new IllegalStateException("Cannot delete user: assigned as head of region. Reassign region first.");
        }
        if (user.getUserType().equals(UserType.HEAD_OF_POS)) {
            throw new IllegalStateException("Cannot delete user: assigned as head of POS. Reassign POS first.");
        }
    }

    public User assignRoleToUser(Integer userId, Integer roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        // Initialize roles list if null
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }

        // Add role if not already assigned
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }
        return userRepository.save(user);
    }

    public User assignRolesToUser(Integer userId, List<Integer> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Role> roles = roleRepository.findAllById(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new IllegalArgumentException("One or more roles not found");
        }

        user.setRoles(roles);

        return userRepository.save(user);
    }
    public User removeRoleFromUser(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        if (user.getRoles() != null) {
            user.getRoles().remove(role);
        }

        return userRepository.save(user);
    }

    // Method 5: Remove multiple roles from user
    public User removeRolesFromUser(Integer userId, List<Integer> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Role> rolesToRemove = roleRepository.findAllById(roleIds);
        if (rolesToRemove.size() != roleIds.size()) {
            throw new IllegalArgumentException("One or more roles not found");
        }

        if (user.getRoles() != null) {
            user.getRoles().removeAll(rolesToRemove);
        }

        return userRepository.save(user);
    }

    // Method 6: Remove all roles from user
    public User removeAllRolesFromUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (user.getRoles() != null) {
            user.getRoles().clear();
        }

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
        userRepository.deleteById(id);
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
        user.getRoles().add(role);
        userRepository.save(user);
    }

    // Unassign Role
    public void unassignRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.getRoles().removeIf(r -> r.getId().equals(roleId));
        userRepository.save(user);
    }

    // ✅ Assign User to Enterprise
    @Transactional
    public void assignUserToEnterprise(Integer userId, Integer enterpriseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        if(enterprise.getUsersInEnterprise().contains(user)){
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
        enterprise.getUsersInEnterprise().removeIf(u -> u.getId().equals(userId));
        enterpriseRepository.save(enterprise);
    }

    @Transactional
    public List<User> getUsersByPOS(Integer posId) {
        return userRepository.findByPosId(posId);
    }

  /*  // ✅ Assign User to Sector
    @Transactional
    public void assignUserToSector(Integer userId, Integer sectorId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        sector.getUsersInSector().add(user);
        sectorRepository.save(sector);
    }

    @Transactional
    public void unassignUserFromSector(Integer userId, Integer sectorId) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        sector.getUsersInSector().removeIf(u -> u.getId().equals(userId));
        sectorRepository.save(sector);
    }

    // ✅ Assign User to Zone
    @Transactional
    public void assignUserToZone(Integer userId, Integer zoneId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        zone.getUsersInZone().add(user);
        zoneRepository.save(zone);
    }

    @Transactional
    public void unassignUserFromZone(Integer userId, Integer zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        zone.getUsersInZone().removeIf(u -> u.getId().equals(userId));
        zoneRepository.save(zone);
    }

    // ✅ Assign User to Region
    @Transactional
    public void assignUserToRegion(Integer userId, Integer regionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        region.getUsersInRegion().add(user);
        regionRepository.save(region);
    }

    @Transactional
    public void unassignUserFromRegion(Integer userId, Integer regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        region.getUsersInRegion().removeIf(u -> u.getId().equals(userId));
        regionRepository.save(region);
    }

  /*  // ✅ Assign Animator to User
    @Transactional
    public void assignAnimatorToUser(Integer userId, Integer animatorId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Animator animator = animatorRepository.findById(animatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Animator not found"));
        user.getAnimators().add(animator);
        userRepository.save(user);
    }

    // ✅ Unassign Animator from User
    @Transactional
    public void unassignAnimatorFromUser(Integer userId, Integer animatorId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.getAnimators().removeIf(a -> a.getId().equals(animatorId));
        userRepository.save(user);
    }

    // Assign Animator
    public void assignAnimator(Integer userId, Integer animatorId) {
        User user = userRepository.findById(userId).orElseThrow();
        Animator animator = animatorRepository.findById(animatorId).orElseThrow();
        user.getAnimators().add(animator);
        userRepository.save(user);
    }

    // Unassign Animator
    public void unassignAnimator(Integer userId, Integer animatorId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.getAnimators().removeIf(a -> a.getId().equals(animatorId));
        userRepository.save(user);
    }
*/

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
}
