package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UpdateEnterpriseRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer enterpriseAdminId;
    private Set<Integer> userIds;
    private Set<Integer> pointsOfSaleIds;
    private Set<Integer> sectorIds;

    public UpdateEnterpriseRequest(@NotNull Integer id, String name, String description, Integer enterpriseAdminId, Set<Integer> userIds, Set<Integer> pointsOfSaleIds, Set<Integer> sectorIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enterpriseAdminId = enterpriseAdminId;
        this.userIds = userIds;
        this.pointsOfSaleIds = pointsOfSaleIds;
        this.sectorIds = sectorIds;
    }

    public UpdateEnterpriseRequest() {
    }

    public static UpdateEnterpriseRequestBuilder builder() {
        return new UpdateEnterpriseRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateEnterpriseRequest)) return false;
        final UpdateEnterpriseRequest other = (UpdateEnterpriseRequest) o;
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
        final Object this$enterpriseAdminId = this.getEnterpriseAdminId();
        final Object other$enterpriseAdminId = other.getEnterpriseAdminId();
        if (this$enterpriseAdminId == null ? other$enterpriseAdminId != null : !this$enterpriseAdminId.equals(other$enterpriseAdminId))
            return false;
        final Object this$userIds = this.getUserIds();
        final Object other$userIds = other.getUserIds();
        if (this$userIds == null ? other$userIds != null : !this$userIds.equals(other$userIds)) return false;
        final Object this$pointsOfSaleIds = this.getPointsOfSaleIds();
        final Object other$pointsOfSaleIds = other.getPointsOfSaleIds();
        if (this$pointsOfSaleIds == null ? other$pointsOfSaleIds != null : !this$pointsOfSaleIds.equals(other$pointsOfSaleIds))
            return false;
        final Object this$sectorIds = this.getSectorIds();
        final Object other$sectorIds = other.getSectorIds();
        if (this$sectorIds == null ? other$sectorIds != null : !this$sectorIds.equals(other$sectorIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateEnterpriseRequest;
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
        final Object $enterpriseAdminId = this.getEnterpriseAdminId();
        result = result * PRIME + ($enterpriseAdminId == null ? 43 : $enterpriseAdminId.hashCode());
        final Object $userIds = this.getUserIds();
        result = result * PRIME + ($userIds == null ? 43 : $userIds.hashCode());
        final Object $pointsOfSaleIds = this.getPointsOfSaleIds();
        result = result * PRIME + ($pointsOfSaleIds == null ? 43 : $pointsOfSaleIds.hashCode());
        final Object $sectorIds = this.getSectorIds();
        result = result * PRIME + ($sectorIds == null ? 43 : $sectorIds.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateEnterpriseRequest(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", enterpriseAdminId=" + this.getEnterpriseAdminId() + ", userIds=" + this.getUserIds() + ", pointsOfSaleIds=" + this.getPointsOfSaleIds() + ", sectorIds=" + this.getSectorIds() + ")";
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

    public Integer getEnterpriseAdminId() {
        return this.enterpriseAdminId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public Set<Integer> getPointsOfSaleIds() {
        return this.pointsOfSaleIds;
    }

    public Set<Integer> getSectorIds() {
        return this.sectorIds;
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

    public void setEnterpriseAdminId(Integer enterpriseAdminId) {
        this.enterpriseAdminId = enterpriseAdminId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setPointsOfSaleIds(Set<Integer> pointsOfSaleIds) {
        this.pointsOfSaleIds = pointsOfSaleIds;
    }

    public void setSectorIds(Set<Integer> sectorIds) {
        this.sectorIds = sectorIds;
    }

    public static class UpdateEnterpriseRequestBuilder {
        private @NotNull Integer id;
        private String name;
        private String description;
        private Integer enterpriseAdminId;
        private Set<Integer> userIds;
        private Set<Integer> pointsOfSaleIds;
        private Set<Integer> sectorIds;

        UpdateEnterpriseRequestBuilder() {
        }

        public UpdateEnterpriseRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdateEnterpriseRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateEnterpriseRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateEnterpriseRequestBuilder enterpriseAdminId(Integer enterpriseAdminId) {
            this.enterpriseAdminId = enterpriseAdminId;
            return this;
        }

        public UpdateEnterpriseRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public UpdateEnterpriseRequestBuilder pointsOfSaleIds(Set<Integer> pointsOfSaleIds) {
            this.pointsOfSaleIds = pointsOfSaleIds;
            return this;
        }

        public UpdateEnterpriseRequestBuilder sectorIds(Set<Integer> sectorIds) {
            this.sectorIds = sectorIds;
            return this;
        }

        public UpdateEnterpriseRequest build() {
            return new UpdateEnterpriseRequest(this.id, this.name, this.description, this.enterpriseAdminId, this.userIds, this.pointsOfSaleIds, this.sectorIds);
        }

        public String toString() {
            return "UpdateEnterpriseRequest.UpdateEnterpriseRequestBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", enterpriseAdminId=" + this.enterpriseAdminId + ", userIds=" + this.userIds + ", pointsOfSaleIds=" + this.pointsOfSaleIds + ", sectorIds=" + this.sectorIds + ")";
        }
    }
}