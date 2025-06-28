package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UpdateSectorRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer sectorHeadId;
    private Set<Integer> userIds;
    private Integer enterpriseId;
    private Set<Integer> zoneIds;

    public UpdateSectorRequest(@NotNull Integer id, String name, String description, Integer sectorHeadId, Set<Integer> userIds, Integer enterpriseId, Set<Integer> zoneIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectorHeadId = sectorHeadId;
        this.userIds = userIds;
        this.enterpriseId = enterpriseId;
        this.zoneIds = zoneIds;
    }

    public UpdateSectorRequest() {
    }

    public static UpdateSectorRequestBuilder builder() {
        return new UpdateSectorRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateSectorRequest)) return false;
        final UpdateSectorRequest other = (UpdateSectorRequest) o;
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
        final Object this$sectorHeadId = this.getSectorHeadId();
        final Object other$sectorHeadId = other.getSectorHeadId();
        if (this$sectorHeadId == null ? other$sectorHeadId != null : !this$sectorHeadId.equals(other$sectorHeadId))
            return false;
        final Object this$userIds = this.getUserIds();
        final Object other$userIds = other.getUserIds();
        if (this$userIds == null ? other$userIds != null : !this$userIds.equals(other$userIds)) return false;
        final Object this$enterpriseId = this.getEnterpriseId();
        final Object other$enterpriseId = other.getEnterpriseId();
        if (this$enterpriseId == null ? other$enterpriseId != null : !this$enterpriseId.equals(other$enterpriseId))
            return false;
        final Object this$zoneIds = this.getZoneIds();
        final Object other$zoneIds = other.getZoneIds();
        if (this$zoneIds == null ? other$zoneIds != null : !this$zoneIds.equals(other$zoneIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateSectorRequest;
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
        final Object $sectorHeadId = this.getSectorHeadId();
        result = result * PRIME + ($sectorHeadId == null ? 43 : $sectorHeadId.hashCode());
        final Object $userIds = this.getUserIds();
        result = result * PRIME + ($userIds == null ? 43 : $userIds.hashCode());
        final Object $enterpriseId = this.getEnterpriseId();
        result = result * PRIME + ($enterpriseId == null ? 43 : $enterpriseId.hashCode());
        final Object $zoneIds = this.getZoneIds();
        result = result * PRIME + ($zoneIds == null ? 43 : $zoneIds.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateSectorRequest(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", sectorHeadId=" + this.getSectorHeadId() + ", userIds=" + this.getUserIds() + ", enterpriseId=" + this.getEnterpriseId() + ", zoneIds=" + this.getZoneIds() + ")";
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

    public Integer getSectorHeadId() {
        return this.sectorHeadId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public Integer getEnterpriseId() {
        return this.enterpriseId;
    }

    public Set<Integer> getZoneIds() {
        return this.zoneIds;
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

    public void setSectorHeadId(Integer sectorHeadId) {
        this.sectorHeadId = sectorHeadId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setZoneIds(Set<Integer> zoneIds) {
        this.zoneIds = zoneIds;
    }

    public static class UpdateSectorRequestBuilder {
        private @NotNull Integer id;
        private String name;
        private String description;
        private Integer sectorHeadId;
        private Set<Integer> userIds;
        private Integer enterpriseId;
        private Set<Integer> zoneIds;

        UpdateSectorRequestBuilder() {
        }

        public UpdateSectorRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdateSectorRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateSectorRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateSectorRequestBuilder sectorHeadId(Integer sectorHeadId) {
            this.sectorHeadId = sectorHeadId;
            return this;
        }

        public UpdateSectorRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public UpdateSectorRequestBuilder enterpriseId(Integer enterpriseId) {
            this.enterpriseId = enterpriseId;
            return this;
        }

        public UpdateSectorRequestBuilder zoneIds(Set<Integer> zoneIds) {
            this.zoneIds = zoneIds;
            return this;
        }

        public UpdateSectorRequest build() {
            return new UpdateSectorRequest(this.id, this.name, this.description, this.sectorHeadId, this.userIds, this.enterpriseId, this.zoneIds);
        }

        public String toString() {
            return "UpdateSectorRequest.UpdateSectorRequestBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", sectorHeadId=" + this.sectorHeadId + ", userIds=" + this.userIds + ", enterpriseId=" + this.enterpriseId + ", zoneIds=" + this.zoneIds + ")";
        }
    }
}