package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateSectorRequest {
    @NotBlank(message = "Sector name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Integer sectorHeadId;

    @NotNull(message = "Enterprise ID is required")
    private Integer enterpriseId;

    public CreateSectorRequest(@NotBlank(message = "Sector name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name, @Size(max = 500, message = "Description cannot exceed 500 characters") String description, Integer sectorHeadId, @NotNull(message = "Enterprise ID is required") Integer enterpriseId) {
        this.name = name;
        this.description = description;
        this.sectorHeadId = sectorHeadId;
        this.enterpriseId = enterpriseId;
    }

    public CreateSectorRequest() {
    }

    public static CreateSectorRequestBuilder builder() {
        return new CreateSectorRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateSectorRequest)) return false;
        final CreateSectorRequest other = (CreateSectorRequest) o;
        if (!other.canEqual((Object) this)) return false;
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
        final Object this$enterpriseId = this.getEnterpriseId();
        final Object other$enterpriseId = other.getEnterpriseId();
        if (this$enterpriseId == null ? other$enterpriseId != null : !this$enterpriseId.equals(other$enterpriseId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateSectorRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $sectorHeadId = this.getSectorHeadId();
        result = result * PRIME + ($sectorHeadId == null ? 43 : $sectorHeadId.hashCode());
        final Object $enterpriseId = this.getEnterpriseId();
        result = result * PRIME + ($enterpriseId == null ? 43 : $enterpriseId.hashCode());
        return result;
    }

    public String toString() {
        return "CreateSectorRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", sectorHeadId=" + this.getSectorHeadId() + ", enterpriseId=" + this.getEnterpriseId() + ")";
    }

    public @NotBlank(message = "Sector name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return this.name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return this.description;
    }

    public Integer getSectorHeadId() {
        return this.sectorHeadId;
    }

    public @NotNull(message = "Enterprise ID is required") Integer getEnterpriseId() {
        return this.enterpriseId;
    }

    public void setName(@NotBlank(message = "Sector name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public void setSectorHeadId(Integer sectorHeadId) {
        this.sectorHeadId = sectorHeadId;
    }

    public void setEnterpriseId(@NotNull(message = "Enterprise ID is required") Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public static class CreateSectorRequestBuilder {
        private @NotBlank(message = "Sector name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name;
        private @Size(max = 500, message = "Description cannot exceed 500 characters") String description;
        private Integer sectorHeadId;
        private @NotNull(message = "Enterprise ID is required") Integer enterpriseId;

        CreateSectorRequestBuilder() {
        }

        public CreateSectorRequestBuilder name(@NotBlank(message = "Sector name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
            this.name = name;
            return this;
        }

        public CreateSectorRequestBuilder description(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
            this.description = description;
            return this;
        }

        public CreateSectorRequestBuilder sectorHeadId(Integer sectorHeadId) {
            this.sectorHeadId = sectorHeadId;
            return this;
        }

        public CreateSectorRequestBuilder enterpriseId(@NotNull(message = "Enterprise ID is required") Integer enterpriseId) {
            this.enterpriseId = enterpriseId;
            return this;
        }

        public CreateSectorRequest build() {
            return new CreateSectorRequest(this.name, this.description, this.sectorHeadId, this.enterpriseId);
        }

        public String toString() {
            return "CreateSectorRequest.CreateSectorRequestBuilder(name=" + this.name + ", description=" + this.description + ", sectorHeadId=" + this.sectorHeadId + ", enterpriseId=" + this.enterpriseId + ")";
        }
    }
}