package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flash_file")
public class FlashFile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;
    
    @Column(name = "stored_file_name", nullable = false)
    private String storedFileName;
    
    @Column(name = "file_type", nullable = false)
    private String fileType;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "file_path", nullable = false)
    private String filePath;
    
    @Column(name = "file_category")
    @Enumerated(EnumType.STRING)
    private FileCategory fileCategory;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flash_id", nullable = false)
    private Flash flash;
    
    // Constructors
    public FlashFile() {}
    
    public FlashFile(String originalFileName, String storedFileName, String fileType, 
                    Long fileSize, String filePath, FileCategory fileCategory) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileCategory = fileCategory;
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
    
    public Flash getFlash() {
        return flash;
    }
    
    public void setFlash(Flash flash) {
        this.flash = flash;
    }
}
