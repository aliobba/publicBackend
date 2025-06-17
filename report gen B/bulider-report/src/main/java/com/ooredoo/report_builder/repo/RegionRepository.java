package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByName(String name);
    List<Region> findByRegionHeadId(Integer headId);
    boolean existsByName(String name);
    List<Region> findByZoneIdsContaining(Integer zoneId);
    List<Region> findByPointsOfSaleIdsContaining(Integer posId);
}