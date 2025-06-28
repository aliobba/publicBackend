package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UpdateRegionRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer regionHeadId;
    private Set<Integer> userIds;
    private Integer zoneId;
    private Set<Integer> pointsOfSaleIds;

    public UpdateRegionRequest(@NotNull Integer id, String name, String description, Integer regionHeadId, Set<Integer> userIds, Integer zoneId, Set<Integer> pointsOfSaleIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.regionHeadId = regionHeadId;
        this.userIds = userIds;
        this.zoneId = zoneId;
        this.pointsOfSaleIds = pointsOfSaleIds;
    }

    public UpdateRegionRequest() {
    }

    public static UpdateRegionRequestBuilder builder() {
        return new UpdateRegionRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateRegionRequest)) return false;
        final UpdateRegionRequest other = (UpdateRegionRequest) o;
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
        final Object this$regionHeadId = this.getRegionHeadId();
        final Object other$regionHeadId = other.getRegionHeadId();
        if (this$regionHeadId == null ? other$regionHeadId != null : !this$regionHeadId.equals(other$regionHeadId))
            return false;
        final Object this$userIds = this.getUserIds();
        final Object other$userIds = other.getUserIds();
        if (this$userIds == null ? other$userIds != null : !this$userIds.equals(other$userIds)) return false;
        final Object this$zoneId = this.getZoneId();
        final Object other$zoneId = other.getZoneId();
        if (this$zoneId == null ? other$zoneId != null : !this$zoneId.equals(other$zoneId)) return false;
        final Object this$pointsOfSaleIds = this.getPointsOfSaleIds();
        final Object other$pointsOfSaleIds = other.getPointsOfSaleIds();
        if (this$pointsOfSaleIds == null ? other$pointsOfSaleIds != null : !this$pointsOfSaleIds.equals(other$pointsOfSaleIds))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateRegionRequest;
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
        final Object $regionHeadId = this.getRegionHeadId();
        result = result * PRIME + ($regionHeadId == null ? 43 : $regionHeadId.hashCode());
        final Object $userIds = this.getUserIds();
        result = result * PRIME + ($userIds == null ? 43 : $userIds.hashCode());
        final Object $zoneId = this.getZoneId();
        result = result * PRIME + ($zoneId == null ? 43 : $zoneId.hashCode());
        final Object $pointsOfSaleIds = this.getPointsOfSaleIds();
        result = result * PRIME + ($pointsOfSaleIds == null ? 43 : $pointsOfSaleIds.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateRegionRequest(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", regionHeadId=" + this.getRegionHeadId() + ", userIds=" + this.getUserIds() + ", zoneId=" + this.getZoneId() + ", pointsOfSaleIds=" + this.getPointsOfSaleIds() + ")";
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

    public Integer getRegionHeadId() {
        return this.regionHeadId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public Integer getZoneId() {
        return this.zoneId;
    }

    public Set<Integer> getPointsOfSaleIds() {
        return this.pointsOfSaleIds;
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

    public void setRegionHeadId(Integer regionHeadId) {
        this.regionHeadId = regionHeadId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public void setPointsOfSaleIds(Set<Integer> pointsOfSaleIds) {
        this.pointsOfSaleIds = pointsOfSaleIds;
    }

    public static class UpdateRegionRequestBuilder {
        private @NotNull Integer id;
        private String name;
        private String description;
        private Integer regionHeadId;
        private Set<Integer> userIds;
        private Integer zoneId;
        private Set<Integer> pointsOfSaleIds;

        UpdateRegionRequestBuilder() {
        }

        public UpdateRegionRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdateRegionRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateRegionRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateRegionRequestBuilder regionHeadId(Integer regionHeadId) {
            this.regionHeadId = regionHeadId;
            return this;
        }

        public UpdateRegionRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public UpdateRegionRequestBuilder zoneId(Integer zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        public UpdateRegionRequestBuilder pointsOfSaleIds(Set<Integer> pointsOfSaleIds) {
            this.pointsOfSaleIds = pointsOfSaleIds;
            return this;
        }

        public UpdateRegionRequest build() {
            return new UpdateRegionRequest(this.id, this.name, this.description, this.regionHeadId, this.userIds, this.zoneId, this.pointsOfSaleIds);
        }

        public String toString() {
            return "UpdateRegionRequest.UpdateRegionRequestBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", regionHeadId=" + this.regionHeadId + ", userIds=" + this.userIds + ", zoneId=" + this.zoneId + ", pointsOfSaleIds=" + this.pointsOfSaleIds + ")";
        }
    }
}