package com.ooredoo.report_builder.dto.request;

import java.util.Set;

public class UpdateAnimatorRoleRequest {
    private String name;
    private String description;
    private Set<Integer> actionIds;

    public UpdateAnimatorRoleRequest(String name, String description, Set<Integer> actionIds) {
        this.name = name;
        this.description = description;
        this.actionIds = actionIds;
    }

    public UpdateAnimatorRoleRequest() {
    }

    public static UpdateAnimatorRoleRequestBuilder builder() {
        return new UpdateAnimatorRoleRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateAnimatorRoleRequest)) return false;
        final UpdateAnimatorRoleRequest other = (UpdateAnimatorRoleRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$actionIds = this.getActionIds();
        final Object other$actionIds = other.getActionIds();
        if (this$actionIds == null ? other$actionIds != null : !this$actionIds.equals(other$actionIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateAnimatorRoleRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $actionIds = this.getActionIds();
        result = result * PRIME + ($actionIds == null ? 43 : $actionIds.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateAnimatorRoleRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", actionIds=" + this.getActionIds() + ")";
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Integer> getActionIds() {
        return this.actionIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActionIds(Set<Integer> actionIds) {
        this.actionIds = actionIds;
    }

    public static class UpdateAnimatorRoleRequestBuilder {
        private String name;
        private String description;
        private Set<Integer> actionIds;

        UpdateAnimatorRoleRequestBuilder() {
        }

        public UpdateAnimatorRoleRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateAnimatorRoleRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateAnimatorRoleRequestBuilder actionIds(Set<Integer> actionIds) {
            this.actionIds = actionIds;
            return this;
        }

        public UpdateAnimatorRoleRequest build() {
            return new UpdateAnimatorRoleRequest(this.name, this.description, this.actionIds);
        }

        public String toString() {
            return "UpdateAnimatorRoleRequest.UpdateAnimatorRoleRequestBuilder(name=" + this.name + ", description=" + this.description + ", actionIds=" + this.actionIds + ")";
        }
    }
}