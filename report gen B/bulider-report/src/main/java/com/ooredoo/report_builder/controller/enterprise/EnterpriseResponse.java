package com.ooredoo.report_builder.controller.enterprise;

import com.ooredoo.report_builder.controller.department.UserSummaryResponse;

import java.time.LocalDateTime;
import java.util.List;

public class EnterpriseResponse {
    private Integer id;
    private String name;
    private String description;
    private String logo;
    private String primaryColor;
    private String secondaryColor;
    private UserSummaryResponse admin;
    private List<UserSummaryResponse> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }

    public String getSecondaryColor() { return secondaryColor; }
    public void setSecondaryColor(String secondaryColor) { this.secondaryColor = secondaryColor; }

    public UserSummaryResponse getAdmin() { return admin; }
    public void setAdmin(UserSummaryResponse admin) { this.admin = admin; }

    public List<UserSummaryResponse> getUsers() { return users; }
    public void setUsers(List<UserSummaryResponse> users) { this.users = users; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}