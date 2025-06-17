package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.AnimatorRole;
import com.ooredoo.report_builder.entity.RoleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimatorRoleRepository extends JpaRepository<AnimatorRole, Integer> {
    Optional<AnimatorRole> findByName(String name);
    List<AnimatorRole> findByActionsContaining(RoleAction action);
    boolean existsByName(String name);
}