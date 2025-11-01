package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.RoleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleActionRepository extends JpaRepository<RoleAction, Integer> {

    //List<RoleAction> findByActionK(String actionKey);
    Optional<RoleAction> findByActionKey(String actionKey);

    boolean existsByActionKey(String actionKey);
    //List<RoleAction> findByActionNameContainingIgnoreCase(String keyword);
}