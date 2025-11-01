package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Flash;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashRepository extends JpaRepository<Flash, Integer> {
    
    // Find all active flashes
    @Query("SELECT f FROM Flash f WHERE f.isActive = true ORDER BY f.creation_Date DESC")
    List<Flash> findByIsActiveTrueOrderByCreation_DateDesc();
    
    // Find all flashes by creator
    @Query("SELECT f FROM Flash f WHERE f.createdBy = :createdBy ORDER BY f.creation_Date DESC")
    List<Flash> findByCreatedByOrderByCreation_DateDesc(@Param("createdBy") User createdBy);
    
    // Find flash with files
    @EntityGraph(attributePaths = {"files"})
    Optional<Flash> findWithFilesById(Integer id);
    
    // Find flash with creator and files
    @EntityGraph(attributePaths = {"createdBy", "files"})
    Optional<Flash> findWithCreatorAndFilesById(Integer id);
    
    // Search flashes by title
    @Query("SELECT f FROM Flash f WHERE " +
            "LOWER(f.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "AND f.isActive = true")
    List<Flash> searchFlashes(@Param("query") String query);
    
    // Find flashes by file category
    @Query("SELECT DISTINCT f FROM Flash f " +
            "JOIN f.files ff " +
            "WHERE ff.fileCategory = :fileCategory " +
            "AND f.isActive = true")
    List<Flash> findFlashesByFileCategory(@Param("fileCategory") String fileCategory);
    
    // Count flashes by creator
    @Query("SELECT COUNT(f) FROM Flash f WHERE f.createdBy.id = :userId")
    Long countByCreatedBy(@Param("userId") Integer userId);
    
    // Find recent flashes
    @Query("SELECT f FROM Flash f WHERE f.isActive = true ORDER BY f.creation_Date DESC")
    List<Flash> findRecentFlashes();
}
