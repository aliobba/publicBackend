package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String role);

    boolean existsByName(String name);

}
