package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.response.EnterpriseResponseDTO;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.EnterpriseRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private UserRepository userRepository;

    public EnterpriseService() {
    }

    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    public Enterprise findById(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        return enterprise;
    }

    public Optional<Enterprise> findByName(String name) {
        return enterpriseRepository.findByName(name);
    }

    public Enterprise createEnterprise(Enterprise enterprise) {

        if (enterprise.getManager().getId() != null) {
            User manager = userRepository.findById(enterprise.getManager().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            if (manager.getUserType().equals(UserType.USER_ADMIN)){
                manager.setEnterprise(enterprise);
                enterprise.setManager(manager);
                enterprise.setUsersInEnterprise((Set<User>) manager);
            }
        }
        return enterpriseRepository.save(enterprise);
    }


    public void deleteById(Integer id) {
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        enterpriseRepository.delete(enterprise);
    }

    public boolean existsByName(String name) {
        return enterpriseRepository.existsByName(name);
    }
    // 🔹 Assign user to enterprise
    @Transactional
    public Enterprise assignUserToEnterprise(Integer enterpriseId, Integer userId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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

        enterprise.getUsersInEnterprise().remove(user);
        return enterpriseRepository.save(enterprise);
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