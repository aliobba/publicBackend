package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Region;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByName(String name);
    boolean existsByName(String name);
    //Optional<Region> findByManagerId(Integer headOfRegionId);;
    boolean existsByHeadOfRegionId(Integer userId);
    //List<Region> findByHeadOfRegionIsNull();

}