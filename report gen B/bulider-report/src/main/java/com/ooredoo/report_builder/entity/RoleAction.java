package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_actions")
@EntityListeners(AuditingEntityListener.class)
public class RoleAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    // Action type (e.g., READ, WRITE, DELETE, etc.)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    // Resource type this action applies to (e.g., FORM, USER, REPORT)
    @Column(nullable = false)
    private String resourceType;

    // Role this action belongs to
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private AnimatorRole role;

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public enum ActionType {
        READ,
        WRITE,
        UPDATE,
        DELETE,
        EXECUTE,
        ASSIGN
    }

    public RoleAction() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public AnimatorRole getRole() {
        return role;
    }

    public void setRole(AnimatorRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}