package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //List<User> findAvailableHeads(UserType userType);
    List<User> findByUserType(UserType userType);

    Optional<User> findByEmail(String email);

    List<User> findByEnabledTrue();

    // Find users available to be heads (not already assigned as heads)
    @Query("SELECT u FROM User u WHERE u.userType = :userType AND u.id NOT IN " +
            "(SELECT r.headOfRegion.id FROM Region r WHERE r.headOfRegion IS NOT NULL) AND " +
            "u.id NOT IN (SELECT z.headOfZone.id FROM Zone z WHERE z.headOfZone IS NOT NULL) AND " +
            "u.id NOT IN (SELECT s.headOfSector.id FROM Sector s WHERE s.headOfSector IS NOT NULL) AND " +
            "u.id NOT IN (SELECT p.headOfPOS.id FROM POS p WHERE p.headOfPOS IS NOT NULL)")
    List<User> findAvailableHeads(@Param("userType") UserType userType);

    // Find users available to be heads by role name (not already assigned as heads)
    @Query("SELECT u FROM User u JOIN u.role r WHERE r.name = :roleName AND u.id NOT IN " +
            "(SELECT reg.headOfRegion.id FROM Region reg WHERE reg.headOfRegion IS NOT NULL) AND " +
            "u.id NOT IN (SELECT z.headOfZone.id FROM Zone z WHERE z.headOfZone IS NOT NULL) AND " +
            "u.id NOT IN (SELECT s.headOfSector.id FROM Sector s WHERE s.headOfSector IS NOT NULL) AND " +
            "u.id NOT IN (SELECT p.headOfPOS.id FROM POS p WHERE p.headOfPOS IS NOT NULL)")
    List<User> findAvailableHeadsByRole(@Param("roleName") String roleName);

    List<User> findByEnterpriseId(Integer enterpriseId);

    List<User> findByPosId(Integer posId);

    // --- New for finding all under a Zone (including Regions) ---
  /*  @Query("""
        SELECT DISTINCT u FROM User u
        LEFT JOIN u.zone z
        LEFT JOIN z.regions r
        WHERE z.id = :zoneId OR r.id IN (
            SELECT r2.id FROM Region r2 WHERE r2.zone.id = :zoneId
        )
    """)
    List<User> findAllUsersInZoneWithRegions(@Param("zoneId") Integer zoneId);
*/
    // --- New for finding all under a Sector (including Zones & Regions) ---
    @Query("""
                SELECT DISTINCT u FROM User u
                JOIN u.pos p
                JOIN p.sector s
                JOIN s.zone z
                JOIN z.region r
                WHERE r.id = :regionId
            """)
    List<User> findAllUsersInRegionFull(@Param("regionId") Integer regionId);

    // Search users by name or email
    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.first_Name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(u.last_Name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(CONCAT(u.first_Name, ' ', u.last_Name)) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<User> searchUsers(@Param("query") String query);

}
