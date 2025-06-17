package com.ooredoo.report_builder.controller.department;

import java.time.LocalDateTime;
import java.util.List;

public class DepartmentResponse {
    private Integer id;
    private String name;
    private String description;
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

    public UserSummaryResponse getAdmin() { return admin; }
    public void setAdmin(UserSummaryResponse admin) { this.admin = admin; }

    public List<UserSummaryResponse> getUsers() { return users; }
    public void setUsers(List<UserSummaryResponse> users) { this.users = users; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
