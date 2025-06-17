package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.request.CreateEnterpriseRequest;
import com.ooredoo.report_builder.dto.request.UpdateEnterpriseRequest;
import com.ooredoo.report_builder.dto.response.EnterpriseResponse;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.enums.RoleType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.EnterpriseMapper;
import com.ooredoo.report_builder.repo.EnterpriseRepository;
import com.ooredoo.report_builder.repo.POSRepository;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.repo.SectorRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.Role;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SectorRepository sectorRepository;
    private final POSRepository posRepository;
    private final EnterpriseMapper enterpriseMapper;

    public EnterpriseService(
            EnterpriseRepository enterpriseRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            SectorRepository sectorRepository,
            POSRepository posRepository,
            EnterpriseMapper enterpriseMapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.sectorRepository = sectorRepository;
        this.posRepository = posRepository;
        this.enterpriseMapper = enterpriseMapper;
    }

    public EnterpriseResponse createEnterprise(CreateEnterpriseRequest request) {
        if (enterpriseRepository.existsByName(request.getName())) {
            throw new RuntimeException("Enterprise with this name already exists");
        }

        // Create new enterprise entity using mapper
        Enterprise enterprise = enterpriseMapper.toEntity(request);
        
        // Set enterprise admin if provided
        if (request.getEnterpriseAdminId() != null) {
            User admin = userRepository.findById(request.getEnterpriseAdminId())
                    .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

            // Assign enterprise admin role if not already assigned
            Role enterpriseAdminRole = roleRepository.findByName(RoleType.ENTREPRISE_ADMIN.getValue())
                    .orElseThrow(() -> new RuntimeException("Enterprise admin role not found"));

            if (!admin.getRoles().contains(enterpriseAdminRole)) {
                admin.getRoles().add(enterpriseAdminRole);
                userRepository.save(admin);
            }

            enterprise.setEnterpriseAdmin(admin);
            
            // Update admin's enterprise if not set
            if (admin.getEnterprise() == null || !admin.getEnterprise().equals(enterprise)) {
                admin.setEnterprise(enterprise);
                userRepository.save(admin);
            }
        }

        enterprise = enterpriseRepository.save(enterprise);
        return enterpriseMapper.toResponse(enterprise);
    }

    public EnterpriseResponse updateEnterprise(Integer enterpriseId, UpdateEnterpriseRequest request) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));

        // Update basic fields using mapper
        enterpriseMapper.updateEntityFromRequest(request, enterprise);
        
        // Update enterprise admin if provided
        if (request.getEnterpriseAdminId() != null) {
            User newAdmin = userRepository.findById(request.getEnterpriseAdminId())
                    .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

            // Assign enterprise admin role if not already assigned
            Role enterpriseAdminRole = roleRepository.findByName(RoleType.ENTREPRISE_ADMIN.getValue())
                    .orElseThrow(() -> new RuntimeException("Enterprise admin role not found"));

            if (!newAdmin.getRoles().contains(enterpriseAdminRole)) {
                newAdmin.getRoles().add(enterpriseAdminRole);
                userRepository.save(newAdmin);
            }

            enterprise.setEnterpriseAdmin(newAdmin);
            
            // Update admin's enterprise if not set
            if (newAdmin.getEnterprise() == null || !newAdmin.getEnterprise().equals(enterprise)) {
                newAdmin.setEnterprise(enterprise);
                userRepository.save(newAdmin);
            }
        }

        // Update users if provided
        if (request.getUserIds() != null && !request.getUserIds().isEmpty()) {
            Set<User> users = new HashSet<>();
            for (Integer userId : request.getUserIds()) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
                users.add(user);
                
                // Update user's enterprise
                if (user.getEnterprise() == null || !user.getEnterprise().equals(enterprise)) {
                    user.setEnterprise(enterprise);
                    userRepository.save(user);
                }
            }
            enterprise.setUsers(users);
        }

        // Update points of sale if provided
        if (request.getPointsOfSaleIds() != null && !request.getPointsOfSaleIds().isEmpty()) {
            Set<POS> pointsOfSale = new HashSet<>();
            for (Integer posId : request.getPointsOfSaleIds()) {
                POS pos = posRepository.findById(posId)
                        .orElseThrow(() -> new ResourceNotFoundException("POS not found: " + posId));
                pointsOfSale.add(pos);
                
                // Update POS's enterprise
                if (pos.getEnterprise() == null || !pos.getEnterprise().equals(enterprise)) {
                    pos.setEnterprise(enterprise);
                    posRepository.save(pos);
                }
            }
            enterprise.setPointsOfSale(pointsOfSale);
        }

        // Update sectors if provided
        if (request.getSectorIds() != null && !request.getSectorIds().isEmpty()) {
            Set<Sector> sectors = new HashSet<>();
            for (Integer sectorId : request.getSectorIds()) {
                Sector sector = sectorRepository.findById(sectorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Sector not found: " + sectorId));
                sectors.add(sector);
                
                // Update sector's enterprise
                if (sector.getEnterprise() == null || !sector.getEnterprise().equals(enterprise)) {
                    sector.setEnterprise(enterprise);
                    sectorRepository.save(sector);
                }
            }
            enterprise.setSectors(sectors);
        }

        enterprise = enterpriseRepository.save(enterprise);
        return enterpriseMapper.toResponse(enterprise);
    }

    public void deleteEnterprise(Integer enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        
        // Remove enterprise reference from users
        if (enterprise.getUsers() != null && !enterprise.getUsers().isEmpty()) {
            enterprise.getUsers().forEach(user -> {
                user.setEnterprise(null);
                userRepository.save(user);
            });
        }
        
        // Remove enterprise reference from points of sale
        if (enterprise.getPointsOfSale() != null && !enterprise.getPointsOfSale().isEmpty()) {
            enterprise.getPointsOfSale().forEach(pos -> {
                pos.setEnterprise(null);
                posRepository.save(pos);
            });
        }
        
        // Remove enterprise reference from sectors
        if (enterprise.getSectors() != null && !enterprise.getSectors().isEmpty()) {
            enterprise.getSectors().forEach(sector -> {
                sector.setEnterprise(null);
                sectorRepository.save(sector);
            });
        }
        
        enterpriseRepository.delete(enterprise);
    }

    public void addUserToEnterprise(Integer userId, Integer enterpriseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));

        user.setEnterprise(enterprise);
        userRepository.save(user);
    }

    public EnterpriseResponse changeEnterpriseAdmin(Integer enterpriseId, Integer newAdminId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));

        User newAdmin = userRepository.findById(newAdminId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Assign enterprise admin role if not already assigned
        Role enterpriseAdminRole = roleRepository.findByName(RoleType.ENTREPRISE_ADMIN.getValue())
                .orElseThrow(() -> new RuntimeException("Enterprise admin role not found"));

        if (!newAdmin.getRoles().contains(enterpriseAdminRole)) {
            newAdmin.getRoles().add(enterpriseAdminRole);
            userRepository.save(newAdmin);
        }

        enterprise.setEnterpriseAdmin(newAdmin);
        enterprise = enterpriseRepository.save(enterprise);

        // Update admin's enterprise
        newAdmin.setEnterprise(enterprise);
        userRepository.save(newAdmin);

        return enterpriseMapper.toResponse(enterprise);
    }

    public List<EnterpriseResponse> getAllEnterprises() {
        return enterpriseRepository.findAll().stream()
                .map(enterpriseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EnterpriseResponse getEnterpriseById(Integer enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        return enterpriseMapper.toResponse(enterprise);
    }

    public List<EnterpriseResponse> getEnterprisesByAdminId(Integer adminId) {
        return enterpriseRepository.findByEnterpriseAdminId(adminId).stream()
                .map(enterpriseMapper::toResponse)
                .collect(Collectors.toList());
    }


}