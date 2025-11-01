package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
    Optional<Sector> findBySectorName(String name);

    boolean existsBySectorName(String name);

    List<Sector> findByHeadOfSectorIsNull();

    boolean existsByHeadOfSectorId(Integer userId);

    List<Sector> findByZoneId(Integer zoneId);

    //List<Sector> findByEnterpriseId(Integer enterpriseId);

    //List<Sector> findByHeadOfSectorIsNull();

    //Optional<Sector> findByHeadOfSector(Integer headOfSectorId);

    // get manager
    // Fetch all users in a sector (direct + zones + regions)
  /* @Query("""
        SELECT DISTINCT u FROM User u
        LEFT JOIN u.sector s
        LEFT JOIN u.zone z
        LEFT JOIN u.region r
        WHERE s.id = :sectorId
           OR z.sector.id = :sectorId
           OR r.zone.sector.id = :sectorId
    """)
    List<User> findAllUsersInSectorFull(@Param("sectorId") Integer sectorId);*/
}