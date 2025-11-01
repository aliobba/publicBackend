package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.EnterpriseRequestDTO;
import com.ooredoo.report_builder.dto.EnterpriseUpdateRequestDTO;
import com.ooredoo.report_builder.dto.EnterpriseResponseDTO;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.EnterpriseMapper;
import com.ooredoo.report_builder.repo.EnterpriseRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    public EnterpriseService() {
    }

    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    // New method to get all enterprises with proper data fetching
    public List<EnterpriseResponseDTO> findAllWithDetails() {
        List<Enterprise> enterprises = enterpriseRepository.findAll();
        return enterprises.stream()
                .map(enterprise -> {
                    // Force fetch of manager and users using separate queries
                    Enterprise enterpriseWithDetails = enterpriseRepository.findById(enterprise.getId()).orElse(enterprise);
                    
                    // Get manager details
                    if (enterpriseWithDetails.getManager() != null) {
                        User manager = userRepository.findById(enterpriseWithDetails.getManager().getId()).orElse(null);
                        enterpriseWithDetails.setManager(manager);
                    }
                    
                    // Get users in enterprise
                    List<User> usersInEnterprise = enterpriseRepository.findAllUsersInEnterpriseFull(enterprise.getId());
                    enterpriseWithDetails.setUsersInEnterprise(new java.util.HashSet<>(usersInEnterprise));
                    
                    return enterpriseMapper.toDto(enterpriseWithDetails);
                })
                .collect(java.util.stream.Collectors.toList());
    }

    // New method to get enterprise by ID with proper data fetching
    public EnterpriseResponseDTO findByIdWithDetails(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        
        // Get manager details
        if (enterprise.getManager() != null) {
            User manager = userRepository.findById(enterprise.getManager().getId()).orElse(null);
            enterprise.setManager(manager);
        }
        
        // Get users in enterprise
        List<User> usersInEnterprise = enterpriseRepository.findAllUsersInEnterpriseFull(id);
        enterprise.setUsersInEnterprise(new java.util.HashSet<>(usersInEnterprise));
        
        return enterpriseMapper.toDto(enterprise);
    }

    public Enterprise findById(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        return enterprise;
    }

    public Optional<Enterprise> findByName(String name) {
        return enterpriseRepository.findByEnterpriseName(name);
    }

    public Enterprise createEnterprise(EnterpriseRequestDTO request) {
        // Validate required fields
        if (request.getEnterpriseName() == null || request.getEnterpriseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Enterprise name is required");
        }

        log.info("Received enterprise request: {}", request);
        log.info("Manager ID from request: {}", request.getManagerId());

        // Create new Enterprise entity
        Enterprise enterprise = new Enterprise();
        enterprise.setEnterpriseName(request.getEnterpriseName());
        enterprise.setLogoUrl(request.getLogoUrl());
        enterprise.setPrimaryColor(request.getPrimaryColor());
        enterprise.setSecondaryColor(request.getSecondaryColor());

        // Handle manager assignment if provided
        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

            // Check if manager has ENTERPRISE_ADMIN role
            boolean hasEnterpriseAdminRole = manager.getRole() != null 
                    && "ENTERPRISE_ADMIN".equals(manager.getRole().getName());
            
            if (!hasEnterpriseAdminRole) {
                throw new IllegalArgumentException("Manager must have ENTERPRISE_ADMIN role");
            }

            // Assign manager to enterprise
            manager.setEnterprise(enterprise);
            enterprise.setManager(manager);
            enterprise.getUsersInEnterprise().add(manager);
            
            log.info("Assigned manager {} to enterprise {}", manager.getId(), enterprise.getEnterpriseName());
        }
        
        log.info("Creating enterprise: {}", enterprise.getEnterpriseName());
        log.info("Creating enterprise manager: {}", enterprise.getManager() != null ? enterprise.getManager().getId() : "No manager");
        return enterpriseRepository.save(enterprise);
    }

    // Keep the old method for backward compatibility
    public Enterprise createEnterprise(Enterprise enterprise) {
        // Validate required fields
        if (enterprise.getEnterpriseName() == null || enterprise.getEnterpriseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Enterprise name is required");
        }

        log.info("Received enterprise manager: {}", enterprise.getManager());
        if (enterprise.getManager() != null) {
            log.info("Manager ID: {}", enterprise.getManager().getId());
        }

        if (enterprise.getManager() != null && enterprise.getManager().getId() != null) {
            User manager = userRepository.findById(enterprise.getManager().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

            // Check if manager has ENTERPRISE_ADMIN role
            boolean hasEnterpriseAdminRole = manager.getRole() != null 
                    && "ENTERPRISE_ADMIN".equals(manager.getRole().getName());
            
            if (!hasEnterpriseAdminRole) {
                throw new IllegalArgumentException("Manager must have ENTERPRISE_ADMIN role");
            }

            // Assign manager to enterprise
            manager.setEnterprise(enterprise);
            enterprise.setManager(manager);
            enterprise.getUsersInEnterprise().add(manager);
            
            log.info("Assigned manager {} to enterprise {}", manager.getId(), enterprise.getEnterpriseName());
        }
        
        log.info("Creating enterprise: {}", enterprise.getEnterpriseName());
        log.info("Creating enterprise manager: {}", enterprise.getManager() != null ? enterprise.getManager().getId() : "No manager");
        return enterpriseRepository.save(enterprise);
    }

    public Enterprise updateEnterprise(Integer id, EnterpriseUpdateRequestDTO request) {
        log.info("Updating enterprise with ID: {}", id);
        log.info("Update request: {}", request);
        log.info("Manager ID from request: {}", request.getManagerId());
        
        Enterprise existingEnterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        
        // Update fields if provided
        if (request.getEnterpriseName() != null) {
            existingEnterprise.setEnterpriseName(request.getEnterpriseName());
        }
        if (request.getLogoUrl() != null) {
            existingEnterprise.setLogoUrl(request.getLogoUrl());
        }
        if (request.getPrimaryColor() != null) {
            existingEnterprise.setPrimaryColor(request.getPrimaryColor());
        }
        if (request.getSecondaryColor() != null) {
            existingEnterprise.setSecondaryColor(request.getSecondaryColor());
        }
        
        // Handle manager update if provided
        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

            // Check if manager has ENTERPRISE_ADMIN role
            boolean hasEnterpriseAdminRole = manager.getRole() != null 
                    && "ENTERPRISE_ADMIN".equals(manager.getRole().getName());
            
            if (!hasEnterpriseAdminRole) {
                throw new IllegalArgumentException("Manager must have ENTERPRISE_ADMIN role");
            }

            // Remove old manager if exists
            if (existingEnterprise.getManager() != null) {
                existingEnterprise.getManager().setEnterprise(null);
                existingEnterprise.getUsersInEnterprise().remove(existingEnterprise.getManager());
            }
            
            // Assign new manager
            manager.setEnterprise(existingEnterprise);
            existingEnterprise.setManager(manager);
            existingEnterprise.getUsersInEnterprise().add(manager);
            
            log.info("Updated manager {} for enterprise {}", manager.getId(), existingEnterprise.getEnterpriseName());
        }
        
        log.info("Updating enterprise: {}", existingEnterprise.getEnterpriseName());
        log.info("Updating enterprise manager: {}", existingEnterprise.getManager() != null ? existingEnterprise.getManager().getId() : "No manager");
        return enterpriseRepository.save(existingEnterprise);
    }

    // Keep the old method for backward compatibility
    public Enterprise updateEnterprise(Enterprise enterprise) {
        log.info("Updating enterprise: {}", enterprise.getEnterpriseName());
        log.info("Updating enterprise manager: {}", enterprise.getManager() != null ? enterprise.getManager().getId() : "No manager");
        return enterpriseRepository.save(enterprise);
    }

    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }


    public void deleteById(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        enterpriseRepository.delete(enterprise);
    }

    public boolean existsByName(String name) {
        return enterpriseRepository.existsByEnterpriseName(name);
    }

    // 🔹 Assign user to enterprise
    @Transactional
    public Enterprise assignUserToEnterprise(Integer enterpriseId, Integer userId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (enterprise.getUsersInEnterprise().contains(user)) {
            throw new ResourceNotFoundException("user already assigned to Enterprise");
        }
        user.setEnterprise(enterprise);
        userRepository.save(user);
        enterprise.getUsersInEnterprise().add(user);
        return enterpriseRepository.save(enterprise);
    }

    // 🔹 Unassign user from enterprise
    @Transactional
    public Enterprise unassignUserFromEnterprise(Integer enterpriseId, Integer userId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!enterprise.getUsersInEnterprise().contains(user)) {
            throw new ResourceNotFoundException("User is not assigned to this enterprise");
        }
        
        // Use the UserService method which handles both sides of the relationship
        userService.unassignUserFromEnterprise(userId, enterpriseId);
        
        // Refresh the enterprise to get updated user list
        enterprise = enterpriseRepository.findById(enterpriseId).orElse(enterprise);
        return enterprise;
    }

    public List<User> getUserInEnterprise(Integer enterpriseId) {
        return enterpriseRepository.findAllUsersInEnterpriseFull(enterpriseId);
    }

    // Search enterprises by query (name, etc.)
    public List<Enterprise> searchEnterprises(String query) {
        if (query == null || query.trim().isEmpty()) {
            return findAll();
        }
        return enterpriseRepository.searchEnterprises(query.trim());
    }

    // Bulk delete enterprises
    @Transactional
    public void bulkDeleteEnterprises(List<Integer> enterpriseIds) {
        for (Integer enterpriseId : enterpriseIds) {
            Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
            // Check if enterprise has users before deletion
            if (!enterprise.getUsersInEnterprise().isEmpty()) {
                throw new IllegalStateException("Cannot delete enterprise with users. Remove users first.");
            }
        }
        enterpriseRepository.deleteAllById(enterpriseIds);
    }
/*
    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;
    private final SectorRepository sectorRepository;
    private final EnterpriseMapper enterpriseMapper;

    // 🔹 Create enterprise
    @Transactional
    public EnterpriseResponseDTO createEnterprise(EnterpriseRequestDTO request) {
        Enterprise enterprise = enterpriseMapper.toEntity(request);

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            enterprise.setHeadOfPOS(manager);
        }

        Enterprise saved = enterpriseRepository.save(enterprise);
        return enterpriseMapper.toDto(saved);
    }

    // 🔹 Update enterprise
    @Transactional
    public EnterpriseResponseDTO updateEnterprise(Integer id, EnterpriseRequestDTO request) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));

        enterprise.setName(request.getName());
        enterprise.setLogoUrl(request.getLogoUrl());
        enterprise.setPrimaryColor(request.getPrimaryColor());
        enterprise.setSecondaryColor(request.getSecondaryColor());

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            enterprise.setHeadOfPOS(manager);
        }

        Enterprise updated = enterpriseRepository.save(enterprise);
        return enterpriseMapper.toDto(updated);
    }

    // 🔹 Delete enterprise
    @Transactional
    public void deleteEnterprise(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        enterpriseRepository.delete(enterprise);
    }

    // 🔹 Get one enterprise
    public EnterpriseResponseDTO getEnterprise(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        return enterpriseMapper.toDto(enterprise);
    }

    // 🔹 Get all enterprises
    public List<EnterpriseResponseDTO> getAllEnterprises() {
        return enterpriseRepository.findAll().stream()
                .map(enterpriseMapper::toDto)
                .collect(Collectors.toList());
    }

    // 🔹 Assign user to enterprise
    @Transactional
    public EnterpriseResponseDTO assignUserToEnterprise(Integer enterpriseId, Integer userId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        enterprise.getUsersInEnterprise().add(user);
        return enterpriseMapper.toDto(enterpriseRepository.save(enterprise));
    }

    // 🔹 Unassign user from enterprise
    @Transactional
    public EnterpriseResponseDTO unassignUserFromEnterprise(Integer enterpriseId, Integer userId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        enterprise.getUsersInEnterprise().remove(user);
        return enterpriseMapper.toDto(enterpriseRepository.save(enterprise));
    }

    // 🔹 Get all sectors in enterprise
    public List<String> getEnterpriseSectors(Integer enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));

        Set<Sector> sectors = enterprise.getSectors();
        return sectors.stream().map(Sector::getName).collect(Collectors.toList());
    }*/
}