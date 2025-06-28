package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.AnimatorRoleDTO;
import com.ooredoo.report_builder.dto.request.CreateAnimatorRoleRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRoleRequest;
import com.ooredoo.report_builder.entity.AnimatorRole;
import com.ooredoo.report_builder.entity.RoleAction;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.AnimatorRoleMapper;
import com.ooredoo.report_builder.repo.AnimatorRoleRepository;
import com.ooredoo.report_builder.repo.RoleActionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class  AnimatorRoleService {

    private final AnimatorRoleRepository animatorRoleRepository;
    private final RoleActionRepository roleActionRepository;
    private final AnimatorRoleMapper animatorRoleMapper;

    public AnimatorRoleService(AnimatorRoleRepository animatorRoleRepository, 
                             RoleActionRepository roleActionRepository,
                             AnimatorRoleMapper animatorRoleMapper) {
        this.animatorRoleRepository = animatorRoleRepository;
        this.roleActionRepository = roleActionRepository;
        this.animatorRoleMapper = animatorRoleMapper;
    }

    public AnimatorRoleDTO createAnimatorRole(CreateAnimatorRoleRequest request) {
        // Check if role with same name already exists
        if (animatorRoleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Animator role with this name already exists");
        }

        // Create new role entity
        AnimatorRole role = animatorRoleMapper.toEntity(request);
        role.setCreatedAt(LocalDateTime.now());
        
        // Set actions if provided
        if (request.getActionIds() != null && !request.getActionIds().isEmpty()) {
            Set<RoleAction> actions = request.getActionIds().stream()
                .map(id -> roleActionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Role action not found with id: " + id)))
                .collect(Collectors.toSet());
            role.setActions(actions);
        }

        // Save and return DTO
        AnimatorRole savedRole = animatorRoleRepository.save(role);
        return animatorRoleMapper.toDTO(savedRole);
    }

    public List<AnimatorRoleDTO> getAllAnimatorRoles() {
        return animatorRoleRepository.findAll().stream()
            .map(animatorRoleMapper::toDTO)
            .collect(Collectors.toList());
    }

    public AnimatorRoleDTO getAnimatorRoleById(Integer id) {
        AnimatorRole role = animatorRoleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Animator role not found with id: " + id));
        return animatorRoleMapper.toDTO(role);
    }

    public AnimatorRoleDTO updateAnimatorRole(Integer id, UpdateAnimatorRoleRequest request) {
        AnimatorRole role = animatorRoleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Animator role not found with id: " + id));

        // Check name uniqueness if name is being updated
        if (request.getName() != null && !request.getName().equals(role.getName()) 
                && animatorRoleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Animator role with this name already exists");
        }

        // Update basic properties
        animatorRoleMapper.updateEntityFromRequest(request, role);
        role.setUpdatedAt(LocalDateTime.now());
        
        // Update actions if provided
        if (request.getActionIds() != null) {
            Set<RoleAction> actions = new HashSet<>();
            if (!request.getActionIds().isEmpty()) {
                actions = request.getActionIds().stream()
                    .map(actionId -> roleActionRepository.findById(actionId)
                        .orElseThrow(() -> new ResourceNotFoundException("Role action not found with id: " + actionId)))
                    .collect(Collectors.toSet());
            }
            role.setActions(actions);
        }

        // Save and return updated DTO
        AnimatorRole updatedRole = animatorRoleRepository.save(role);
        return animatorRoleMapper.toDTO(updatedRole);
    }

    public void deleteAnimatorRole(Integer id) {
        AnimatorRole role = animatorRoleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Animator role not found with id: " + id));
        
        // Check if role is in use by any animators
        if (role.getAnimators() != null && !role.getAnimators().isEmpty()) {
            throw new RuntimeException("Cannot delete role that is assigned to animators");
        }
        
        animatorRoleRepository.delete(role);
    }
}