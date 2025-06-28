package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateEnterpriseRequest {
    @NotBlank(message = "Enterprise name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private String logo;
    private String primaryColor;
    private String secondaryColor;
    private Integer enterpriseAdminId;

    public CreateEnterpriseRequest(@NotBlank(message = "Enterprise name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name, @Size(max = 500, message = "Description cannot exceed 500 characters") String description, String logo, String primaryColor, String secondaryColor, Integer enterpriseAdminId) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.enterpriseAdminId = enterpriseAdminId;
    }

    public CreateEnterpriseRequest() {
    }

    public static CreateEnterpriseRequestBuilder builder() {
        return new CreateEnterpriseRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateEnterpriseRequest)) return false;
        final CreateEnterpriseRequest other = (CreateEnterpriseRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$logo = this.getLogo();
        final Object other$logo = other.getLogo();
        if (this$logo == null ? other$logo != null : !this$logo.equals(other$logo)) return false;
        final Object this$primaryColor = this.getPrimaryColor();
        final Object other$primaryColor = other.getPrimaryColor();
        if (this$primaryColor == null ? other$primaryColor != null : !this$primaryColor.equals(other$primaryColor))
            return false;
        final Object this$secondaryColor = this.getSecondaryColor();
        final Object other$secondaryColor = other.getSecondaryColor();
        if (this$secondaryColor == null ? other$secondaryColor != null : !this$secondaryColor.equals(other$secondaryColor))
            return false;
        final Object this$enterpriseAdminId = this.getEnterpriseAdminId();
        final Object other$enterpriseAdminId = other.getEnterpriseAdminId();
        if (this$enterpriseAdminId == null ? other$enterpriseAdminId != null : !this$enterpriseAdminId.equals(other$enterpriseAdminId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateEnterpriseRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $logo = this.getLogo();
        result = result * PRIME + ($logo == null ? 43 : $logo.hashCode());
        final Object $primaryColor = this.getPrimaryColor();
        result = result * PRIME + ($primaryColor == null ? 43 : $primaryColor.hashCode());
        final Object $secondaryColor = this.getSecondaryColor();
        result = result * PRIME + ($secondaryColor == null ? 43 : $secondaryColor.hashCode());
        final Object $enterpriseAdminId = this.getEnterpriseAdminId();
        result = result * PRIME + ($enterpriseAdminId == null ? 43 : $enterpriseAdminId.hashCode());
        return result;
    }

    public String toString() {
        return "CreateEnterpriseRequest(name=" + this.getName() + ", description=" + this.getDescription() + ", logo=" + this.getLogo() + ", primaryColor=" + this.getPrimaryColor() + ", secondaryColor=" + this.getSecondaryColor() + ", enterpriseAdminId=" + this.getEnterpriseAdminId() + ")";
    }

    public @NotBlank(message = "Enterprise name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return this.name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return this.description;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public Integer getEnterpriseAdminId() {
        return this.enterpriseAdminId;
    }

    public void setName(@NotBlank(message = "Enterprise name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setEnterpriseAdminId(Integer enterpriseAdminId) {
        this.enterpriseAdminId = enterpriseAdminId;
    }

    public static class CreateEnterpriseRequestBuilder {
        private @NotBlank(message = "Enterprise name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name;
        private @Size(max = 500, message = "Description cannot exceed 500 characters") String description;
        private String logo;
        private String primaryColor;
        private String secondaryColor;
        private Integer enterpriseAdminId;

        CreateEnterpriseRequestBuilder() {
        }

        public CreateEnterpriseRequestBuilder name(@NotBlank(message = "Enterprise name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
            this.name = name;
            return this;
        }

        public CreateEnterpriseRequestBuilder description(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
            this.description = description;
            return this;
        }

        public CreateEnterpriseRequestBuilder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public CreateEnterpriseRequestBuilder primaryColor(String primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public CreateEnterpriseRequestBuilder secondaryColor(String secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

        public CreateEnterpriseRequestBuilder enterpriseAdminId(Integer enterpriseAdminId) {
            this.enterpriseAdminId = enterpriseAdminId;
            return this;
        }

        public CreateEnterpriseRequest build() {
            return new CreateEnterpriseRequest(this.name, this.description, this.logo, this.primaryColor, this.secondaryColor, this.enterpriseAdminId);
        }

        public String toString() {
            return "CreateEnterpriseRequest.CreateEnterpriseRequestBuilder(name=" + this.name + ", description=" + this.description + ", logo=" + this.logo + ", primaryColor=" + this.primaryColor + ", secondaryColor=" + this.secondaryColor + ", enterpriseAdminId=" + this.enterpriseAdminId + ")";
        }
    }
}