package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.user.Role;
import com.ooredoo.report_builder.entity.RoleAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    public Role save(Role role) {
        // Validate required fields
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Role name is required");
        }

        // Check if role name already exists (for new roles)
        if (role.getId() == null && roleRepository.existsByName(role.getName())) {
            throw new IllegalArgumentException("Role with name '" + role.getName() + "' already exists");
        }

        // Check if role name already exists for different role (for updates)
        if (role.getId() != null) {
            Optional<Role> existingRole = roleRepository.findByName(role.getName());
            if (existingRole.isPresent() && !existingRole.get().getId().equals(role.getId())) {
                throw new IllegalArgumentException("Role with name '" + role.getName() + "' already exists");
            }
        }

        log.info("Saving role: {}", role.getName());
        return roleRepository.save(role);
    }

    public Role updateRole(Integer id, Role role) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));

        // Update fields
        existingRole.setName(role.getName());
        if (role.getActions() != null) {
            existingRole.setActions(role.getActions());
        }

        log.info("Updating role with id: {}", id);
        return roleRepository.save(existingRole);
    }

    public void deleteById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));

        // Check if role is assigned to any users
        if (role.getUsers() != null && !role.getUsers().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete role that is assigned to users. Please unassign users first.");
        }

        log.info("Deleting role with id: {}", id);
        roleRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + name));

        // Check if role is assigned to any users
        if (role.getUsers() != null && !role.getUsers().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete role that is assigned to users. Please unassign users first.");
        }
        log.info("Deleting role with name: {}", name);
        roleRepository.deleteById(role.getId());
    }

    public Role assignActions(Integer roleId, Set<RoleAction> actions) {
        Optional<Role> role = findById(roleId);
        if (role.isPresent()) {
            role.get().setActions(actions);
            return save(role.get());
        }
        throw new IllegalArgumentException("Role not found with id: " + roleId);
    }
}