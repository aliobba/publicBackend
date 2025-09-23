package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.RoleAction;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.user.Role;
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
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleService() {
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
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
