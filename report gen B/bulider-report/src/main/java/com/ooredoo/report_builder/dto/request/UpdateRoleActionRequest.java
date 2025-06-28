package com.ooredoo.report_builder.dto.request;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;

public class UpdateRoleActionRequest {
    private String name;
    private String description;
    private ActionType actionType;
    private String resourceType;
    private Integer roleId;

    public UpdateRoleActionRequest(String name, String description, ActionType actionType, String resourceType, Integer roleId) {
        this.name = name;
        this.description = description;
        this.actionType = actionType;
        this.resourceType = resourceType;
        this.roleId = roleId;
    }

    public UpdateRoleActionRequest() {
    }

    public static UpdateRoleActionRequestBuilder builder() {
        return new UpdateRoleActionRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateRoleActionRequest)) return false;
        final UpdateRoleActionRequest other = (UpdateRoleActionRequest) o;
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
        return other instanceof UpdateRoleActionRequest;
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
        return "UpdateRoleActionRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", actionType=" + this.getActionType() + ", resourceType=" + this.getResourceType() + ", roleId=" + this.getRoleId() + ")";
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

    public Integer getRoleId() {
        return this.roleId;
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

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public static class UpdateRoleActionRequestBuilder {
        private String name;
        private String description;
        private ActionType actionType;
        private String resourceType;
        private Integer roleId;

        UpdateRoleActionRequestBuilder() {
        }

        public UpdateRoleActionRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateRoleActionRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateRoleActionRequestBuilder actionType(ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public UpdateRoleActionRequestBuilder resourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public UpdateRoleActionRequestBuilder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public UpdateRoleActionRequest build() {
            return new UpdateRoleActionRequest(this.name, this.description, this.actionType, this.resourceType, this.roleId);
        }

        public String toString() {
            return "UpdateRoleActionRequest.UpdateRoleActionRequestBuilder(name=" + this.name + ", description=" + this.description + ", actionType=" + this.actionType + ", resourceType=" + this.resourceType + ", roleId=" + this.roleId + ")";
        }
    }
}