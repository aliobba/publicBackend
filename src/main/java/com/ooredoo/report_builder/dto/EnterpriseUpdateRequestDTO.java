package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnterpriseUpdateRequestDTO {
    
    @Size(min = 2, max = 100, message = "Enterprise name must be between 2 and 100 characters")
    private String enterpriseName;
    
    @Pattern(regexp = "^https?://.+", message = "Logo URL must be a valid HTTP/HTTPS URL")
    private String logoUrl;
    
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Primary color must be a valid hex color")
    private String primaryColor;
    
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Secondary color must be a valid hex color")
    private String secondaryColor;
    
    private Integer managerId;

    // Constructors
    public EnterpriseUpdateRequestDTO() {}

    // Getters and Setters
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "EnterpriseUpdateRequestDTO{" +
                "enterpriseName='" + enterpriseName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", primaryColor='" + primaryColor + '\'' +
                ", secondaryColor='" + secondaryColor + '\'' +
                ", managerId=" + managerId +
                '}';
    }
}
