package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByRegionName(String name);

    boolean existsByRegionName(String name);

    //Optional<Region> findByManagerId(Integer headOfRegionId);;
    boolean existsByHeadOfRegionId(Integer userId);
    //List<Region> findByHeadOfRegionIsNull();

}