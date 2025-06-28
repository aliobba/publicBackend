package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UpdatePOSRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private String address;
    private Integer managerId;
    private Set<Integer> userIds;
    private Integer enterpriseId;
    private Integer regionId;

    public UpdatePOSRequest(@NotNull Integer id, String name, String description, String address, Integer managerId, Set<Integer> userIds, Integer enterpriseId, Integer regionId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.managerId = managerId;
        this.userIds = userIds;
        this.enterpriseId = enterpriseId;
        this.regionId = regionId;
    }

    public UpdatePOSRequest() {
    }

    public static UpdatePOSRequestBuilder builder() {
        return new UpdatePOSRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdatePOSRequest)) return false;
        final UpdatePOSRequest other = (UpdatePOSRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$managerId = this.getManagerId();
        final Object other$managerId = other.getManagerId();
        if (this$managerId == null ? other$managerId != null : !this$managerId.equals(other$managerId)) return false;
        final Object this$userIds = this.getUserIds();
        final Object other$userIds = other.getUserIds();
        if (this$userIds == null ? other$userIds != null : !this$userIds.equals(other$userIds)) return false;
        final Object this$enterpriseId = this.getEnterpriseId();
        final Object other$enterpriseId = other.getEnterpriseId();
        if (this$enterpriseId == null ? other$enterpriseId != null : !this$enterpriseId.equals(other$enterpriseId))
            return false;
        final Object this$regionId = this.getRegionId();
        final Object other$regionId = other.getRegionId();
        if (this$regionId == null ? other$regionId != null : !this$regionId.equals(other$regionId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdatePOSRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $managerId = this.getManagerId();
        result = result * PRIME + ($managerId == null ? 43 : $managerId.hashCode());
        final Object $userIds = this.getUserIds();
        result = result * PRIME + ($userIds == null ? 43 : $userIds.hashCode());
        final Object $enterpriseId = this.getEnterpriseId();
        result = result * PRIME + ($enterpriseId == null ? 43 : $enterpriseId.hashCode());
        final Object $regionId = this.getRegionId();
        result = result * PRIME + ($regionId == null ? 43 : $regionId.hashCode());
        return result;
    }

    public String toString() {
        return "UpdatePOSRequest(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", address=" + this.getAddress() + ", managerId=" + this.getManagerId() + ", userIds=" + this.getUserIds() + ", enterpriseId=" + this.getEnterpriseId() + ", regionId=" + this.getRegionId() + ")";
    }

    public @NotNull Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAddress() {
        return this.address;
    }

    public Integer getManagerId() {
        return this.managerId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public Integer getEnterpriseId() {
        return this.enterpriseId;
    }

    public Integer getRegionId() {
        return this.regionId;
    }

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public static class UpdatePOSRequestBuilder {
        private @NotNull Integer id;
        private String name;
        private String description;
        private String address;
        private Integer managerId;
        private Set<Integer> userIds;
        private Integer enterpriseId;
        private Integer regionId;

        UpdatePOSRequestBuilder() {
        }

        public UpdatePOSRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdatePOSRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdatePOSRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdatePOSRequestBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UpdatePOSRequestBuilder managerId(Integer managerId) {
            this.managerId = managerId;
            return this;
        }

        public UpdatePOSRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public UpdatePOSRequestBuilder enterpriseId(Integer enterpriseId) {
            this.enterpriseId = enterpriseId;
            return this;
        }

        public UpdatePOSRequestBuilder regionId(Integer regionId) {
            this.regionId = regionId;
            return this;
        }

        public UpdatePOSRequest build() {
            return new UpdatePOSRequest(this.id, this.name, this.description, this.address, this.managerId, this.userIds, this.enterpriseId, this.regionId);
        }

        public String toString() {
            return "UpdatePOSRequest.UpdatePOSRequestBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", address=" + this.address + ", managerId=" + this.managerId + ", userIds=" + this.userIds + ", enterpriseId=" + this.enterpriseId + ", regionId=" + this.regionId + ")";
        }
    }
}