package com.ooredoo.report_builder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ooredoo.report_builder.dto.UserInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public class EnterpriseResponseDTO {
    private Integer id;
    private String enterpriseName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private UserInfoDTO manager;
    private List<UserInfoDTO> usersInEnterprise;
    private Integer usersCount;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creation_Date;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt_Date;

    // Constructors
    public EnterpriseResponseDTO() {}

    public EnterpriseResponseDTO(Integer id, String enterpriseName, String logoUrl, 
                               String primaryColor, String secondaryColor, 
                               UserInfoDTO manager, List<UserInfoDTO> usersInEnterprise,
                               LocalDateTime creation_Date, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.enterpriseName = enterpriseName;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.manager = manager;
        this.usersInEnterprise = usersInEnterprise;
        this.usersCount = usersInEnterprise != null ? usersInEnterprise.size() : 0;
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

    public UserInfoDTO getManager() {
        return manager;
    }

    public void setManager(UserInfoDTO manager) {
        this.manager = manager;
    }

    public List<UserInfoDTO> getUsersInEnterprise() {
        return usersInEnterprise;
    }

    public void setUsersInEnterprise(List<UserInfoDTO> usersInEnterprise) {
        this.usersInEnterprise = usersInEnterprise;
        this.usersCount = usersInEnterprise != null ? usersInEnterprise.size() : 0;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
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
