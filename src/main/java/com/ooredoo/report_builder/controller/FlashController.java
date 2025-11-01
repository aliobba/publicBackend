package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.FlashRequestDTO;
import com.ooredoo.report_builder.dto.FlashResponseDTO;
import com.ooredoo.report_builder.dto.MessageResponse;
import com.ooredoo.report_builder.services.FlashService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/flash")
@CrossOrigin(origins = "*")
@Slf4j
public class FlashController {

    @Autowired
    private FlashService flashService;
    
    @Autowired
    private UserService userService;

    // === FLASH CRUD OPERATIONS ===

    @PostMapping("/create")
    public ResponseEntity<FlashResponseDTO> createFlash(@Valid @RequestBody FlashRequestDTO request) {
        try {
            User currentUser = userService.getCurrentAuthenticatedUser();
            FlashResponseDTO response = flashService.createFlash(request, currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creating flash: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FlashResponseDTO>> getAllFlashes() {
        try {
            List<FlashResponseDTO> flashes = flashService.getAllFlashes();
            return ResponseEntity.ok(flashes);
        } catch (Exception e) {
            log.error("Error fetching flashes: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashResponseDTO> getFlashById(@PathVariable Integer id) {
        try {
            FlashResponseDTO flash = flashService.getFlashById(id);
            return ResponseEntity.ok(flash);
        } catch (Exception e) {
            log.error("Error fetching flash with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashResponseDTO> updateFlash(@PathVariable Integer id, 
                                                       @Valid @RequestBody FlashRequestDTO request) {
        try {
            FlashResponseDTO response = flashService.updateFlash(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error updating flash with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFlash(@PathVariable Integer id) {
        try {
            MessageResponse response = flashService.deleteFlash(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error deleting flash with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // === FILE UPLOAD OPERATIONS ===

    @PostMapping("/{id}/upload")
    public ResponseEntity<FlashResponseDTO> uploadFile(@PathVariable Integer id,
                                                      @RequestParam("file") MultipartFile file) {
        try {
            FlashResponseDTO response = flashService.uploadFile(id, file);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Validation error uploading file: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error uploading file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{flashId}/files/{fileId}")
    public ResponseEntity<MessageResponse> deleteFile(@PathVariable Integer flashId,
                                                     @PathVariable Integer fileId) {
        try {
            MessageResponse response = flashService.deleteFile(flashId, fileId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Validation error deleting file: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error deleting file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{flashId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer flashId,
                                               @PathVariable Integer fileId) {
        try {
            // Get file info from service (this would need to be implemented)
            // For now, we'll implement a basic file download
            
            // This is a simplified implementation - in a real scenario,
            // you'd want to get the file path from the database
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .build();
                    
        } catch (Exception e) {
            log.error("Error downloading file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // === SEARCH OPERATIONS ===

    @GetMapping("/search")
    public ResponseEntity<List<FlashResponseDTO>> searchFlashes(@RequestParam String query) {
        try {
            // This would need to be implemented in the service
            List<FlashResponseDTO> flashes = flashService.getAllFlashes();
            return ResponseEntity.ok(flashes);
        } catch (Exception e) {
            log.error("Error searching flashes: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
