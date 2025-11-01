package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.FlashFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashFileRepository extends JpaRepository<FlashFile, Integer> {
    
    // Find files by flash
    List<FlashFile> findByFlashIdOrderByIdAsc(Integer flashId);
    
    // Find files by file category
    List<FlashFile> findByFileCategory(String fileCategory);
    
    // Find file by stored file name
    Optional<FlashFile> findByStoredFileName(String storedFileName);
    
    // Find files by original file name
    List<FlashFile> findByOriginalFileNameContainingIgnoreCase(String fileName);
    
    // Count files by flash
    @Query("SELECT COUNT(ff) FROM FlashFile ff WHERE ff.flash.id = :flashId")
    Long countByFlashId(@Param("flashId") Integer flashId);
    
    // Find files by file type
    @Query("SELECT ff FROM FlashFile ff WHERE ff.fileType = :fileType")
    List<FlashFile> findByFileType(@Param("fileType") String fileType);
    
    // Delete files by flash
    void deleteByFlashId(Integer flashId);
}
