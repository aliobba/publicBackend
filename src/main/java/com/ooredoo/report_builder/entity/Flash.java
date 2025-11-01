package com.ooredoo.report_builder.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flash")
@EntityListeners(AuditingEntityListener.class)
public class Flash {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation_Date;
    
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt_Date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnoreProperties({"createdFlashes"})
    private User createdBy;
    
    @OneToMany(mappedBy = "flash", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlashFile> files = new ArrayList<>();
    
    // Constructors
    public Flash() {}
    
    public Flash(String title, User createdBy) {
        this.title = title;
        this.createdBy = createdBy;
        this.isActive = true;
    }
    
    public Flash(Integer id, String title, Boolean isActive, LocalDateTime creation_Date, 
                LocalDateTime updatedAt_Date, User createdBy, List<FlashFile> files) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
        this.createdBy = createdBy;
        this.files = files;
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
    
    public User getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    
    public List<FlashFile> getFiles() {
        return files;
    }
    
    public void setFiles(List<FlashFile> files) {
        this.files = files;
    }
    
    // Helper methods
    public void addFile(FlashFile file) {
        files.add(file);
        file.setFlash(this);
    }
    
    public void removeFile(FlashFile file) {
        files.remove(file);
        file.setFlash(null);
    }
}
