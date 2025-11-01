package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    Optional<Zone> findByZoneName(String name);

    boolean existsByZoneName(String name);

    List<Zone> findByRegionId(Integer regionId);

    boolean existsByHeadOfZoneId(Integer headOfZoneId);
    //List<Zone> findByHeadOfZoneIsNull();
    //Optional<Zone> findByManagerId(Integer headOfZoneId);
   /* @Query("""
        SELECT DISTINCT u FROM User u
        LEFT JOIN u.zone z
        LEFT JOIN u.region r
        WHERE z.id = :zoneId
           OR r.zone.id = :zoneId
    """)
    List<User> findAllUsersInZoneWithRegions(@Param("zoneId") Integer zoneId);*/
}