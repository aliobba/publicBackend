package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateRegionRequest {
    @NotBlank(message = "Region name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Integer regionHeadId;

    @NotNull(message = "Zone ID is required")
    private Integer zoneId;

    public CreateRegionRequest(@NotBlank(message = "Region name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name, @Size(max = 500, message = "Description cannot exceed 500 characters") String description, Integer regionHeadId, @NotNull(message = "Zone ID is required") Integer zoneId) {
        this.name = name;
        this.description = description;
        this.regionHeadId = regionHeadId;
        this.zoneId = zoneId;
    }

    public CreateRegionRequest() {
    }

    public static CreateRegionRequestBuilder builder() {
        return new CreateRegionRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateRegionRequest)) return false;
        final CreateRegionRequest other = (CreateRegionRequest) o;
        if (!other.canEqual((Object) this)) return false;
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
        final Object this$zoneId = this.getZoneId();
        final Object other$zoneId = other.getZoneId();
        if (this$zoneId == null ? other$zoneId != null : !this$zoneId.equals(other$zoneId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateRegionRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $regionHeadId = this.getRegionHeadId();
        result = result * PRIME + ($regionHeadId == null ? 43 : $regionHeadId.hashCode());
        final Object $zoneId = this.getZoneId();
        result = result * PRIME + ($zoneId == null ? 43 : $zoneId.hashCode());
        return result;
    }

    public String toString() {
        return "CreateRegionRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", regionHeadId=" + this.getRegionHeadId() + ", zoneId=" + this.getZoneId() + ")";
    }

    public @NotBlank(message = "Region name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return this.name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return this.description;
    }

    public Integer getRegionHeadId() {
        return this.regionHeadId;
    }

    public @NotNull(message = "Zone ID is required") Integer getZoneId() {
        return this.zoneId;
    }

    public void setName(@NotBlank(message = "Region name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public void setRegionHeadId(Integer regionHeadId) {
        this.regionHeadId = regionHeadId;
    }

    public void setZoneId(@NotNull(message = "Zone ID is required") Integer zoneId) {
        this.zoneId = zoneId;
    }

    public static class CreateRegionRequestBuilder {
        private @NotBlank(message = "Region name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name;
        private @Size(max = 500, message = "Description cannot exceed 500 characters") String description;
        private Integer regionHeadId;
        private @NotNull(message = "Zone ID is required") Integer zoneId;

        CreateRegionRequestBuilder() {
        }

        public CreateRegionRequestBuilder name(@NotBlank(message = "Region name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
            this.name = name;
            return this;
        }

        public CreateRegionRequestBuilder description(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
            this.description = description;
            return this;
        }

        public CreateRegionRequestBuilder regionHeadId(Integer regionHeadId) {
            this.regionHeadId = regionHeadId;
            return this;
        }

        public CreateRegionRequestBuilder zoneId(@NotNull(message = "Zone ID is required") Integer zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        public CreateRegionRequest build() {
            return new CreateRegionRequest(this.name, this.description, this.regionHeadId, this.zoneId);
        }

        public String toString() {
            return "CreateRegionRequest.CreateRegionRequestBuilder(name=" + this.name + ", description=" + this.description + ", regionHeadId=" + this.regionHeadId + ", zoneId=" + this.zoneId + ")";
        }
    }
}