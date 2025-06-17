package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.POS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface POSRepository extends JpaRepository<POS, Integer> {
    Optional<POS> findByName(String name);
    List<POS> findByRegionId(Integer regionId);
    List<POS> findByEnterpriseId(Integer enterpriseId);
    boolean existsByName(String name);
    List<POS> findByZoneId(Integer zoneId);
}