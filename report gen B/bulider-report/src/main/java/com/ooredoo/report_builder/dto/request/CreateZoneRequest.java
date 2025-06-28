package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateZoneRequest {
    @NotBlank(message = "Zone name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Integer zoneHeadId;

    @NotNull(message = "Sector ID is required")
    private Integer sectorId;

    public CreateZoneRequest(@NotBlank(message = "Zone name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name, @Size(max = 500, message = "Description cannot exceed 500 characters") String description, Integer zoneHeadId, @NotNull(message = "Sector ID is required") Integer sectorId) {
        this.name = name;
        this.description = description;
        this.zoneHeadId = zoneHeadId;
        this.sectorId = sectorId;
    }

    public CreateZoneRequest() {
    }

    public static CreateZoneRequestBuilder builder() {
        return new CreateZoneRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateZoneRequest)) return false;
        final CreateZoneRequest other = (CreateZoneRequest) o;
        if (!other.canEqual((Object) this)) return false;
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
        final Object this$sectorId = this.getSectorId();
        final Object other$sectorId = other.getSectorId();
        if (this$sectorId == null ? other$sectorId != null : !this$sectorId.equals(other$sectorId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateZoneRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $zoneHeadId = this.getZoneHeadId();
        result = result * PRIME + ($zoneHeadId == null ? 43 : $zoneHeadId.hashCode());
        final Object $sectorId = this.getSectorId();
        result = result * PRIME + ($sectorId == null ? 43 : $sectorId.hashCode());
        return result;
    }

    public String toString() {
        return "CreateZoneRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", zoneHeadId=" + this.getZoneHeadId() + ", sectorId=" + this.getSectorId() + ")";
    }

    public @NotBlank(message = "Zone name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return this.name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return this.description;
    }

    public Integer getZoneHeadId() {
        return this.zoneHeadId;
    }

    public @NotNull(message = "Sector ID is required") Integer getSectorId() {
        return this.sectorId;
    }

    public void setName(@NotBlank(message = "Zone name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public void setZoneHeadId(Integer zoneHeadId) {
        this.zoneHeadId = zoneHeadId;
    }

    public void setSectorId(@NotNull(message = "Sector ID is required") Integer sectorId) {
        this.sectorId = sectorId;
    }

    public static class CreateZoneRequestBuilder {
        private @NotBlank(message = "Zone name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name;
        private @Size(max = 500, message = "Description cannot exceed 500 characters") String description;
        private Integer zoneHeadId;
        private @NotNull(message = "Sector ID is required") Integer sectorId;

        CreateZoneRequestBuilder() {
        }

        public CreateZoneRequestBuilder name(@NotBlank(message = "Zone name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
            this.name = name;
            return this;
        }

        public CreateZoneRequestBuilder description(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
            this.description = description;
            return this;
        }

        public CreateZoneRequestBuilder zoneHeadId(Integer zoneHeadId) {
            this.zoneHeadId = zoneHeadId;
            return this;
        }

        public CreateZoneRequestBuilder sectorId(@NotNull(message = "Sector ID is required") Integer sectorId) {
            this.sectorId = sectorId;
            return this;
        }

        public CreateZoneRequest build() {
            return new CreateZoneRequest(this.name, this.description, this.zoneHeadId, this.sectorId);
        }

        public String toString() {
            return "CreateZoneRequest.CreateZoneRequestBuilder(name=" + this.name + ", description=" + this.description + ", zoneHeadId=" + this.zoneHeadId + ", sectorId=" + this.sectorId + ")";
        }
    }
}