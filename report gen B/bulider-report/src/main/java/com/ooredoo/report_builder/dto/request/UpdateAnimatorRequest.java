package com.ooredoo.report_builder.dto.request;

import java.util.Set;

public class UpdateAnimatorRequest {
    private String pin;
    private String description;
    private Integer roleId;
    private Integer posId;
    private Set<Integer> userIds;

    public UpdateAnimatorRequest(String pin, String description, Integer roleId, Integer posId, Set<Integer> userIds) {
        this.pin = pin;
        this.description = description;
        this.roleId = roleId;
        this.posId = posId;
        this.userIds = userIds;
    }

    public UpdateAnimatorRequest() {
    }

    public static UpdateAnimatorRequestBuilder builder() {
        return new UpdateAnimatorRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateAnimatorRequest)) return false;
        final UpdateAnimatorRequest other = (UpdateAnimatorRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$pin = this.getPin();
        final Object other$pin = other.getPin();
        if (this$pin == null ? other$pin != null : !this$pin.equals(other$pin)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$roleId = this.getRoleId();
        final Object other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
        final Object this$posId = this.getPosId();
        final Object other$posId = other.getPosId();
        if (this$posId == null ? other$posId != null : !this$posId.equals(other$posId)) return false;
        final Object this$userIds = this.getUserIds();
        final Object other$userIds = other.getUserIds();
        if (this$userIds == null ? other$userIds != null : !this$userIds.equals(other$userIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateAnimatorRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $pin = this.getPin();
        result = result * PRIME + ($pin == null ? 43 : $pin.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $roleId = this.getRoleId();
        result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
        final Object $posId = this.getPosId();
        result = result * PRIME + ($posId == null ? 43 : $posId.hashCode());
        final Object $userIds = this.getUserIds();
        result = result * PRIME + ($userIds == null ? 43 : $userIds.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateAnimatorRequest(pin=" + this.getPin() + ", description=" + this.getDescription() + ", roleId=" + this.getRoleId() + ", posId=" + this.getPosId() + ", userIds=" + this.getUserIds() + ")";
    }

    public String getPin() {
        return this.pin;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public Integer getPosId() {
        return this.posId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public static class UpdateAnimatorRequestBuilder {
        private String pin;
        private String description;
        private Integer roleId;
        private Integer posId;
        private Set<Integer> userIds;

        UpdateAnimatorRequestBuilder() {
        }

        public UpdateAnimatorRequestBuilder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public UpdateAnimatorRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateAnimatorRequestBuilder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public UpdateAnimatorRequestBuilder posId(Integer posId) {
            this.posId = posId;
            return this;
        }

        public UpdateAnimatorRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public UpdateAnimatorRequest build() {
            return new UpdateAnimatorRequest(this.pin, this.description, this.roleId, this.posId, this.userIds);
        }

        public String toString() {
            return "UpdateAnimatorRequest.UpdateAnimatorRequestBuilder(pin=" + this.pin + ", description=" + this.description + ", roleId=" + this.roleId + ", posId=" + this.posId + ", userIds=" + this.userIds + ")";
        }
    }
}