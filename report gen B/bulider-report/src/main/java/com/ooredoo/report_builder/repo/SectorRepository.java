package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
    Optional<Sector> findByName(String name);
    List<Sector> findByEnterpriseId(Integer enterpriseId);
    boolean existsByName(String name);
    List<Sector> findByZoneId(Integer zoneId);
}