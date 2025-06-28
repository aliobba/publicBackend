package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreatePOSRequest {
    @NotBlank(message = "POS name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location cannot exceed 200 characters")
    private String location;

    private Integer managerId;

    @NotNull(message = "Enterprise ID is required")
    private Integer enterpriseId;

    private Integer regionId;

    public CreatePOSRequest(@NotBlank(message = "POS name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name, @Size(max = 500, message = "Description cannot exceed 500 characters") String description, @NotBlank(message = "Location is required") @Size(max = 200, message = "Location cannot exceed 200 characters") String location, Integer managerId, @NotNull(message = "Enterprise ID is required") Integer enterpriseId, Integer regionId) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.managerId = managerId;
        this.enterpriseId = enterpriseId;
        this.regionId = regionId;
    }

    public CreatePOSRequest() {
    }

    public static CreatePOSRequestBuilder builder() {
        return new CreatePOSRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreatePOSRequest)) return false;
        final CreatePOSRequest other = (CreatePOSRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        final Object this$managerId = this.getManagerId();
        final Object other$managerId = other.getManagerId();
        if (this$managerId == null ? other$managerId != null : !this$managerId.equals(other$managerId)) return false;
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
        return other instanceof CreatePOSRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        final Object $managerId = this.getManagerId();
        result = result * PRIME + ($managerId == null ? 43 : $managerId.hashCode());
        final Object $enterpriseId = this.getEnterpriseId();
        result = result * PRIME + ($enterpriseId == null ? 43 : $enterpriseId.hashCode());
        final Object $regionId = this.getRegionId();
        result = result * PRIME + ($regionId == null ? 43 : $regionId.hashCode());
        return result;
    }

    public String toString() {
        return "CreatePOSRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", location=" + this.getLocation() + ", managerId=" + this.getManagerId() + ", enterpriseId=" + this.getEnterpriseId() + ", regionId=" + this.getRegionId() + ")";
    }

    public @NotBlank(message = "POS name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return this.name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return this.description;
    }

    public @NotBlank(message = "Location is required") @Size(max = 200, message = "Location cannot exceed 200 characters") String getLocation() {
        return this.location;
    }

    public Integer getManagerId() {
        return this.managerId;
    }

    public @NotNull(message = "Enterprise ID is required") Integer getEnterpriseId() {
        return this.enterpriseId;
    }

    public Integer getRegionId() {
        return this.regionId;
    }

    public void setName(@NotBlank(message = "POS name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public void setLocation(@NotBlank(message = "Location is required") @Size(max = 200, message = "Location cannot exceed 200 characters") String location) {
        this.location = location;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setEnterpriseId(@NotNull(message = "Enterprise ID is required") Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public static class CreatePOSRequestBuilder {
        private @NotBlank(message = "POS name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name;
        private @Size(max = 500, message = "Description cannot exceed 500 characters") String description;
        private @NotBlank(message = "Location is required")
        @Size(max = 200, message = "Location cannot exceed 200 characters") String location;
        private Integer managerId;
        private @NotNull(message = "Enterprise ID is required") Integer enterpriseId;
        private Integer regionId;

        CreatePOSRequestBuilder() {
        }

        public CreatePOSRequestBuilder name(@NotBlank(message = "POS name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
            this.name = name;
            return this;
        }

        public CreatePOSRequestBuilder description(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
            this.description = description;
            return this;
        }

        public CreatePOSRequestBuilder location(@NotBlank(message = "Location is required") @Size(max = 200, message = "Location cannot exceed 200 characters") String location) {
            this.location = location;
            return this;
        }

        public CreatePOSRequestBuilder managerId(Integer managerId) {
            this.managerId = managerId;
            return this;
        }

        public CreatePOSRequestBuilder enterpriseId(@NotNull(message = "Enterprise ID is required") Integer enterpriseId) {
            this.enterpriseId = enterpriseId;
            return this;
        }

        public CreatePOSRequestBuilder regionId(Integer regionId) {
            this.regionId = regionId;
            return this;
        }

        public CreatePOSRequest build() {
            return new CreatePOSRequest(this.name, this.description, this.location, this.managerId, this.enterpriseId, this.regionId);
        }

        public String toString() {
            return "CreatePOSRequest.CreatePOSRequestBuilder(name=" + this.name + ", description=" + this.description + ", location=" + this.location + ", managerId=" + this.managerId + ", enterpriseId=" + this.enterpriseId + ", regionId=" + this.regionId + ")";
        }
    }
}