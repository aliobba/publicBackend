package com.ooredoo.report_builder.dto.response;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;

import java.time.LocalDateTime;


public class RoleActionResponse {
    private Integer id;
    private String name;
    private String description;
    private ActionType actionType;
    private String resourceType;
    private AnimatorRoleSummaryDTO role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RoleActionResponse(Integer id, String name, String description, ActionType actionType, String resourceType, AnimatorRoleSummaryDTO role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.actionType = actionType;
        this.resourceType = resourceType;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RoleActionResponse() {
    }

    public static RoleActionResponseBuilder builder() {
        return new RoleActionResponseBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public AnimatorRoleSummaryDTO getRole() {
        return this.role;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setRole(AnimatorRoleSummaryDTO role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class RoleActionResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private ActionType actionType;
        private String resourceType;
        private AnimatorRoleSummaryDTO role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        RoleActionResponseBuilder() {
        }

        public RoleActionResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RoleActionResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RoleActionResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RoleActionResponseBuilder actionType(ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public RoleActionResponseBuilder resourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public RoleActionResponseBuilder role(AnimatorRoleSummaryDTO role) {
            this.role = role;
            return this;
        }

        public RoleActionResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleActionResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RoleActionResponse build() {
            return new RoleActionResponse(this.id, this.name, this.description, this.actionType, this.resourceType, this.role, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "RoleActionResponse.RoleActionResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", actionType=" + this.actionType + ", resourceType=" + this.resourceType + ", role=" + this.role + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}