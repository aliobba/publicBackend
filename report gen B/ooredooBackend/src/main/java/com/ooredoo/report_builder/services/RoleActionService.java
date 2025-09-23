package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.RoleAction;
import com.ooredoo.report_builder.repo.RoleActionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RoleActionService {
    @Autowired
    private RoleActionRepository roleActionRepository;

    public RoleActionService() {
    }

    public List<RoleAction> findAll() {
        return roleActionRepository.findAll();
    }

    public Optional<RoleAction> findById(Integer id) {
        return roleActionRepository.findById(id);
    }

    public Optional<RoleAction> findByActionKey(String actionKey) {
        return roleActionRepository.findByActionKey(actionKey);
    }

    public RoleAction save(RoleAction roleAction) {
        return roleActionRepository.save(roleAction);
    }

    public void deleteById(Integer id) {
        roleActionRepository.deleteById(id);
    }

    public boolean existsByActionKey(String actionKey) {
        return roleActionRepository.existsByActionKey(actionKey);
    }

}
