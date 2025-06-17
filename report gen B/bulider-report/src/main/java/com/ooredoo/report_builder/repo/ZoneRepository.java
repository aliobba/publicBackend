package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    Optional<Zone> findByName(String name);
    List<Zone> findByRegionId(Integer regionId);
    boolean existsByName(String name);
    List<Zone> findBySectorIdsContaining(Integer sectorId);
    List<Zone> findByPointsOfSaleIdsContaining(Integer posId);
}