package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FlashRequestDTO {
    
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 200, message = "Title must be between 2 and 200 characters")
    private String title;
    
    private Boolean isActive = true;
    
    // Constructors
    public FlashRequestDTO() {}
    
    public FlashRequestDTO(String title, Boolean isActive) {
        this.title = title;
        this.isActive = isActive;
    }
    
    // Getters and Setters
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
    
    @Override
    public String toString() {
        return "FlashRequestDTO{" +
                "title='" + title + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
