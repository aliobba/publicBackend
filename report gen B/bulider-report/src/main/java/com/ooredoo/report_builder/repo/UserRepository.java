package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.user.User;
import com.ooredoo.report_builder.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    List<User> findByEnterpriseId(Integer enterpriseId);
    List<User> findBySectorId(Integer sectorId);
    List<User> findByZoneId(Integer zoneId);
    List<User> findByRegionId(Integer regionId);
    List<User> findByPosId(Integer posId);
    List<User> findByUserType(UserType userType);
    Optional<User> findByEmail(String email);
}
