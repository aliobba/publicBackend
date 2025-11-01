package com.ooredoo.report_builder.dto;

import com.ooredoo.report_builder.entity.FileCategory;

public class FlashFileDTO {
    private Integer id;
    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private Long fileSize;
    private String filePath;
    private FileCategory fileCategory;
    private String downloadUrl;
    
    // Constructors
    public FlashFileDTO() {}
    
    public FlashFileDTO(Integer id, String originalFileName, String storedFileName, String fileType, 
                       Long fileSize, String filePath, FileCategory fileCategory, String downloadUrl) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileCategory = fileCategory;
        this.downloadUrl = downloadUrl;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOriginalFileName() {
        return originalFileName;
    }
    
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
    
    public String getStoredFileName() {
        return storedFileName;
    }
    
    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public FileCategory getFileCategory() {
        return fileCategory;
    }
    
    public void setFileCategory(FileCategory fileCategory) {
        this.fileCategory = fileCategory;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }
    
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
