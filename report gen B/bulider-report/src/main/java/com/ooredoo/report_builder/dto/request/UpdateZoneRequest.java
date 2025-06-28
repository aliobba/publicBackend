package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UpdateZoneRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer zoneHeadId;
    private Set<Integer> userIds;
    private Integer sectorId;
    private Set<Integer> regionIds;

    public UpdateZoneRequest(@NotNull Integer id, String name, String description, Integer zoneHeadId, Set<Integer> userIds, Integer sectorId, Set<Integer> regionIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.zoneHeadId = zoneHeadId;
        this.userIds = userIds;
        this.sectorId = sectorId;
        this.regionIds = regionIds;
    }

    public UpdateZoneRequest() {
    }

    public static UpdateZoneRequestBuilder builder() {
        return new UpdateZoneRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateZoneRequest)) return false;
        final UpdateZoneRequest other = (UpdateZoneRequest) o;
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
        final Object this$zoneHeadId = this.getZoneHeadId();
        final Object other$zoneHeadId = other.getZoneHeadId();
        if (this$zoneHeadId == null ? other$zoneHeadId != null : !this$zoneHeadId.equals(other$zoneHeadId))
            return false;
        final Object this$userIds = this.getUserIds();
        final Object other$userIds = other.getUserIds();
        if (this$userIds == null ? other$userIds != null : !this$userIds.equals(other$userIds)) return false;
        final Object this$sectorId = this.getSectorId();
        final Object other$sectorId = other.getSectorId();
        if (this$sectorId == null ? other$sectorId != null : !this$sectorId.equals(other$sectorId)) return false;
        final Object this$regionIds = this.getRegionIds();
        final Object other$regionIds = other.getRegionIds();
        if (this$regionIds == null ? other$regionIds != null : !this$regionIds.equals(other$regionIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateZoneRequest;
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
        final Object $zoneHeadId = this.getZoneHeadId();
        result = result * PRIME + ($zoneHeadId == null ? 43 : $zoneHeadId.hashCode());
        final Object $userIds = this.getUserIds();
        result = result * PRIME + ($userIds == null ? 43 : $userIds.hashCode());
        final Object $sectorId = this.getSectorId();
        result = result * PRIME + ($sectorId == null ? 43 : $sectorId.hashCode());
        final Object $regionIds = this.getRegionIds();
        result = result * PRIME + ($regionIds == null ? 43 : $regionIds.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateZoneRequest(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", zoneHeadId=" + this.getZoneHeadId() + ", userIds=" + this.getUserIds() + ", sectorId=" + this.getSectorId() + ", regionIds=" + this.getRegionIds() + ")";
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

    public Integer getZoneHeadId() {
        return this.zoneHeadId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public Integer getSectorId() {
        return this.sectorId;
    }

    public Set<Integer> getRegionIds() {
        return this.regionIds;
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

    public void setZoneHeadId(Integer zoneHeadId) {
        this.zoneHeadId = zoneHeadId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public void setRegionIds(Set<Integer> regionIds) {
        this.regionIds = regionIds;
    }

    public static class UpdateZoneRequestBuilder {
        private @NotNull Integer id;
        private String name;
        private String description;
        private Integer zoneHeadId;
        private Set<Integer> userIds;
        private Integer sectorId;
        private Set<Integer> regionIds;

        UpdateZoneRequestBuilder() {
        }

        public UpdateZoneRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdateZoneRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateZoneRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateZoneRequestBuilder zoneHeadId(Integer zoneHeadId) {
            this.zoneHeadId = zoneHeadId;
            return this;
        }

        public UpdateZoneRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public UpdateZoneRequestBuilder sectorId(Integer sectorId) {
            this.sectorId = sectorId;
            return this;
        }

        public UpdateZoneRequestBuilder regionIds(Set<Integer> regionIds) {
            this.regionIds = regionIds;
            return this;
        }

        public UpdateZoneRequest build() {
            return new UpdateZoneRequest(this.id, this.name, this.description, this.zoneHeadId, this.userIds, this.sectorId, this.regionIds);
        }

        public String toString() {
            return "UpdateZoneRequest.UpdateZoneRequestBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", zoneHeadId=" + this.zoneHeadId + ", userIds=" + this.userIds + ", sectorId=" + this.sectorId + ", regionIds=" + this.regionIds + ")";
        }
    }
}