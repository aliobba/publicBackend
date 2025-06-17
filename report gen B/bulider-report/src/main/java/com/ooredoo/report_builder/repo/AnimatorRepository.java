package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Animator;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimatorRepository extends JpaRepository<Animator, Integer> {
    Optional<Animator> findByPin(String pin);
    List<Animator> findByUsers(User user);
    List<Animator> findByRoleId(Integer roleId);
    boolean existsByPin(String pin);
    List<Animator> findByPosId(Integer posId);
}