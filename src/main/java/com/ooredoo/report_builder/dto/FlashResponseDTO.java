package com.ooredoo.report_builder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ooredoo.report_builder.dto.UserInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public class FlashResponseDTO {
    private Integer id;
    private String title;
    private Boolean isActive;
    private UserInfoDTO createdBy;
    private List<FlashFileDTO> files;
    private Integer filesCount;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creation_Date;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt_Date;
    
    // Constructors
    public FlashResponseDTO() {}
    
    public FlashResponseDTO(Integer id, String title, Boolean isActive, UserInfoDTO createdBy, 
                          List<FlashFileDTO> files, LocalDateTime creation_Date, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.files = files;
        this.filesCount = files != null ? files.size() : 0;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public UserInfoDTO getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(UserInfoDTO createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<FlashFileDTO> getFiles() {
        return files;
    }
    
    public void setFiles(List<FlashFileDTO> files) {
        this.files = files;
        this.filesCount = files != null ? files.size() : 0;
    }
    
    public Integer getFilesCount() {
        return filesCount;
    }
    
    public void setFilesCount(Integer filesCount) {
        this.filesCount = filesCount;
    }
    
    public LocalDateTime getCreation_Date() {
        return creation_Date;
    }
    
    public void setCreation_Date(LocalDateTime creation_Date) {
        this.creation_Date = creation_Date;
    }
    
    public LocalDateTime getUpdatedAt_Date() {
        return updatedAt_Date;
    }
    
    public void setUpdatedAt_Date(LocalDateTime updatedAt_Date) {
        this.updatedAt_Date = updatedAt_Date;
    }
}
