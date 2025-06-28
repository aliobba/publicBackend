package com.ooredoo.report_builder.dto.request;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateRoleActionRequest {
    @NotBlank(message = "Action name is required")
    private String name;

    private String description;

    @NotNull(message = "Action type is required")
    private ActionType actionType;

    @NotBlank(message = "Resource type is required")
    private String resourceType;

    @NotNull(message = "Role ID is required")
    private Integer roleId;

    public CreateRoleActionRequest(@NotBlank(message = "Action name is required") String name, String description, @NotNull(message = "Action type is required") ActionType actionType, @NotBlank(message = "Resource type is required") String resourceType, @NotNull(message = "Role ID is required") Integer roleId) {
        this.name = name;
        this.description = description;
        this.actionType = actionType;
        this.resourceType = resourceType;
        this.roleId = roleId;
    }

    public CreateRoleActionRequest() {
    }

    public static CreateRoleActionRequestBuilder builder() {
        return new CreateRoleActionRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateRoleActionRequest)) return false;
        final CreateRoleActionRequest other = (CreateRoleActionRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$actionType = this.getActionType();
        final Object other$actionType = other.getActionType();
        if (this$actionType == null ? other$actionType != null : !this$actionType.equals(other$actionType))
            return false;
        final Object this$resourceType = this.getResourceType();
        final Object other$resourceType = other.getResourceType();
        if (this$resourceType == null ? other$resourceType != null : !this$resourceType.equals(other$resourceType))
            return false;
        final Object this$roleId = this.getRoleId();
        final Object other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateRoleActionRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $actionType = this.getActionType();
        result = result * PRIME + ($actionType == null ? 43 : $actionType.hashCode());
        final Object $resourceType = this.getResourceType();
        result = result * PRIME + ($resourceType == null ? 43 : $resourceType.hashCode());
        final Object $roleId = this.getRoleId();
        result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
        return result;
    }

    public String toString() {
        return "CreateRoleActionRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", actionType=" + this.getActionType() + ", resourceType=" + this.getResourceType() + ", roleId=" + this.getRoleId() + ")";
    }

    public @NotBlank(message = "Action name is required") String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public @NotNull(message = "Action type is required") ActionType getActionType() {
        return this.actionType;
    }

    public @NotBlank(message = "Resource type is required") String getResourceType() {
        return this.resourceType;
    }

    public @NotNull(message = "Role ID is required") Integer getRoleId() {
        return this.roleId;
    }

    public void setName(@NotBlank(message = "Action name is required") String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActionType(@NotNull(message = "Action type is required") ActionType actionType) {
        this.actionType = actionType;
    }

    public void setResourceType(@NotBlank(message = "Resource type is required") String resourceType) {
        this.resourceType = resourceType;
    }

    public void setRoleId(@NotNull(message = "Role ID is required") Integer roleId) {
        this.roleId = roleId;
    }

    public static class CreateRoleActionRequestBuilder {
        private @NotBlank(message = "Action name is required") String name;
        private String description;
        private @NotNull(message = "Action type is required") ActionType actionType;
        private @NotBlank(message = "Resource type is required") String resourceType;
        private @NotNull(message = "Role ID is required") Integer roleId;

        CreateRoleActionRequestBuilder() {
        }

        public CreateRoleActionRequestBuilder name(@NotBlank(message = "Action name is required") String name) {
            this.name = name;
            return this;
        }

        public CreateRoleActionRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRoleActionRequestBuilder actionType(@NotNull(message = "Action type is required") ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public CreateRoleActionRequestBuilder resourceType(@NotBlank(message = "Resource type is required") String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public CreateRoleActionRequestBuilder roleId(@NotNull(message = "Role ID is required") Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public CreateRoleActionRequest build() {
            return new CreateRoleActionRequest(this.name, this.description, this.actionType, this.resourceType, this.roleId);
        }

        public String toString() {
            return "CreateRoleActionRequest.CreateRoleActionRequestBuilder(name=" + this.name + ", description=" + this.description + ", actionType=" + this.actionType + ", resourceType=" + this.resourceType + ", roleId=" + this.roleId + ")";
        }
    }
}