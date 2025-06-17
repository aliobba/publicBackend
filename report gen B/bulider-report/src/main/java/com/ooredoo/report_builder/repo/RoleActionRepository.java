package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.AnimatorRole;
import com.ooredoo.report_builder.entity.RoleAction;
import com.ooredoo.report_builder.entity.RoleAction.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleActionRepository extends JpaRepository<RoleAction, Integer> {
    Optional<RoleAction> findByName(String name);
    List<RoleAction> findByActionType(ActionType actionType);
    boolean existsByName(String name);
    List<RoleAction> findByRole(AnimatorRole role);
}