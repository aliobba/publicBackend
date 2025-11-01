package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.FlashRequestDTO;
import com.ooredoo.report_builder.dto.FlashResponseDTO;
import com.ooredoo.report_builder.dto.FlashFileDTO;
import com.ooredoo.report_builder.dto.MessageResponse;
import com.ooredoo.report_builder.entity.Flash;
import com.ooredoo.report_builder.entity.FlashFile;
import com.ooredoo.report_builder.entity.FileCategory;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.FlashRepository;
import com.ooredoo.report_builder.repo.FlashFileRepository;
import com.ooredoo.report_builder.user.User;
import com.ooredoo.report_builder.mapper.EnterpriseMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FlashService {

    @Autowired
    private FlashRepository flashRepository;
    
    @Autowired
    private FlashFileRepository flashFileRepository;
    
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    
    @Value("${app.upload.dir:uploads/flash}")
    private String uploadDir;
    
    // Maximum file size in bytes (100MB)
    private static final long MAX_FILE_SIZE = 100 * 1024 * 1024;
    
    // Supported file types
    private static final String[] SUPPORTED_IMAGE_TYPES = {
        "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    };
    
    private static final String[] SUPPORTED_VIDEO_TYPES = {
        "video/mp4", "video/avi", "video/mov", "video/wmv", "video/webm"
    };
    
    private static final String[] SUPPORTED_PDF_TYPES = {
        "application/pdf"
    };

    public FlashService() {}

    // === FLASH CRUD OPERATIONS ===

    public FlashResponseDTO createFlash(FlashRequestDTO request, User creator) {
        log.info("Creating flash with title: {}", request.getTitle());
        
        Flash flash = new Flash();
        flash.setTitle(request.getTitle());
        flash.setIsActive(request.getIsActive());
        flash.setCreatedBy(creator);
        
        flash = flashRepository.save(flash);
        log.info("Flash created successfully with ID: {}", flash.getId());
        
        return mapToResponseDTO(flash);
    }

    public List<FlashResponseDTO> getAllFlashes() {
        List<Flash> flashes = flashRepository.findByIsActiveTrueOrderByCreation_DateDesc();
        return flashes.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public FlashResponseDTO getFlashById(Integer id) {
        Flash flash = flashRepository.findWithCreatorAndFilesById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flash not found with id: " + id));
        
        return mapToResponseDTO(flash);
    }

    public FlashResponseDTO updateFlash(Integer id, FlashRequestDTO request) {
        Flash flash = flashRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flash not found with id: " + id));
        
        flash.setTitle(request.getTitle());
        flash.setIsActive(request.getIsActive());
        
        flash = flashRepository.save(flash);
        log.info("Flash updated successfully with ID: {}", flash.getId());
        
        return mapToResponseDTO(flash);
    }

    public MessageResponse deleteFlash(Integer id) {
        Flash flash = flashRepository.findWithCreatorAndFilesById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flash not found with id: " + id));
        
        // Delete associated files from filesystem
        deleteFlashFiles(flash);
        
        // Delete from database
        flashRepository.delete(flash);
        
        log.info("Flash deleted successfully with ID: {}", id);
        return MessageResponse.success("Flash deleted successfully");
    }

    // === FILE UPLOAD OPERATIONS ===

    public FlashResponseDTO uploadFile(Integer flashId, MultipartFile file) {
        log.info("Uploading file: {} for flash ID: {}", file.getOriginalFilename(), flashId);
        
        Flash flash = flashRepository.findById(flashId)
                .orElseThrow(() -> new ResourceNotFoundException("Flash not found with id: " + flashId));
        
        // Validate file
        validateFile(file);
        
        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            // Generate unique filename
            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);
            String storedFileName = UUID.randomUUID().toString() + fileExtension;
            
            // Save file to filesystem
            Path filePath = uploadPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Determine file category
            FileCategory fileCategory = FileCategory.fromFileType(file.getContentType());
            
            // Create FlashFile entity
            FlashFile flashFile = new FlashFile();
            flashFile.setOriginalFileName(originalFileName);
            flashFile.setStoredFileName(storedFileName);
            flashFile.setFileType(file.getContentType());
            flashFile.setFileSize(file.getSize());
            flashFile.setFilePath(filePath.toString());
            flashFile.setFileCategory(fileCategory);
            flashFile.setFlash(flash);
            
            flashFile = flashFileRepository.save(flashFile);
            flash.addFile(flashFile);
            
            log.info("File uploaded successfully: {}", storedFileName);
            
            return mapToResponseDTO(flash);
            
        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }

    public MessageResponse deleteFile(Integer flashId, Integer fileId) {
        FlashFile flashFile = flashFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id: " + fileId));
        
        if (!flashFile.getFlash().getId().equals(flashId)) {
            throw new IllegalArgumentException("File does not belong to the specified flash");
        }
        
        try {
            // Delete file from filesystem
            Path filePath = Paths.get(flashFile.getFilePath());
            Files.deleteIfExists(filePath);
            
            // Delete from database
            flashFileRepository.delete(flashFile);
            
            log.info("File deleted successfully: {}", flashFile.getStoredFileName());
            return MessageResponse.success("File deleted successfully");
            
        } catch (IOException e) {
            log.error("Error deleting file: {}", e.getMessage());
            throw new RuntimeException("Failed to delete file: " + e.getMessage());
        }
    }

    // === HELPER METHODS ===

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size exceeds maximum allowed size of 100MB");
        }
        
        String contentType = file.getContentType();
        if (!isSupportedFileType(contentType)) {
            throw new IllegalArgumentException("Unsupported file type: " + contentType);
        }
    }

    private boolean isSupportedFileType(String contentType) {
        if (contentType == null) return false;
        
        for (String type : SUPPORTED_IMAGE_TYPES) {
            if (type.equals(contentType)) return true;
        }
        for (String type : SUPPORTED_VIDEO_TYPES) {
            if (type.equals(contentType)) return true;
        }
        for (String type : SUPPORTED_PDF_TYPES) {
            if (type.equals(contentType)) return true;
        }
        
        return false;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private void deleteFlashFiles(Flash flash) {
        for (FlashFile file : flash.getFiles()) {
            try {
                Path filePath = Paths.get(file.getFilePath());
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.warn("Failed to delete file: {}", file.getFilePath());
            }
        }
    }

    private FlashResponseDTO mapToResponseDTO(Flash flash) {
        FlashResponseDTO dto = new FlashResponseDTO();
        dto.setId(flash.getId());
        dto.setTitle(flash.getTitle());
        dto.setIsActive(flash.getIsActive());
        dto.setCreation_Date(flash.getCreation_Date());
        dto.setUpdatedAt_Date(flash.getUpdatedAt_Date());
        
        // Map creator info
        if (flash.getCreatedBy() != null) {
            dto.setCreatedBy(enterpriseMapper.toUserInfoDto(flash.getCreatedBy()));
        }
        
        // Map files
        List<FlashFileDTO> fileDTOs = flash.getFiles().stream()
                .map(this::mapToFileDTO)
                .collect(Collectors.toList());
        dto.setFiles(fileDTOs);
        
        return dto;
    }

    private FlashFileDTO mapToFileDTO(FlashFile file) {
        FlashFileDTO dto = new FlashFileDTO();
        dto.setId(file.getId());
        dto.setOriginalFileName(file.getOriginalFileName());
        dto.setStoredFileName(file.getStoredFileName());
        dto.setFileType(file.getFileType());
        dto.setFileSize(file.getFileSize());
        dto.setFilePath(file.getFilePath());
        dto.setFileCategory(file.getFileCategory());
        
        // Generate download URL
        dto.setDownloadUrl("/api/v1/flash/" + file.getFlash().getId() + "/files/" + file.getId() + "/download");
        
        return dto;
    }
}
