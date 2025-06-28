package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class EnterpriseResponse {
    private Integer id;
    private String name;
    private String description;
    private String logo;
    private String primaryColor;
    private String secondaryColor;
    private UserSummaryDTO enterpriseAdmin;
    private Set<UserSummaryDTO> users;
    private Set<POSSummaryDTO> pointsOfSale;
    private Set<SectorSummaryDTO> sectors;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EnterpriseResponse(Integer id, String name, String description, String logo, String primaryColor, String secondaryColor, UserSummaryDTO enterpriseAdmin, Set<UserSummaryDTO> users, Set<POSSummaryDTO> pointsOfSale, Set<SectorSummaryDTO> sectors, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.enterpriseAdmin = enterpriseAdmin;
        this.users = users;
        this.pointsOfSale = pointsOfSale;
        this.sectors = sectors;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public EnterpriseResponse() {
    }

    public static EnterpriseResponseBuilder builder() {
        return new EnterpriseResponseBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
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

    public UserSummaryDTO getEnterpriseAdmin() {
        return this.enterpriseAdmin;
    }

    public Set<UserSummaryDTO> getUsers() {
        return this.users;
    }

    public Set<POSSummaryDTO> getPointsOfSale() {
        return this.pointsOfSale;
    }

    public Set<SectorSummaryDTO> getSectors() {
        return this.sectors;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
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

    public void setEnterpriseAdmin(UserSummaryDTO enterpriseAdmin) {
        this.enterpriseAdmin = enterpriseAdmin;
    }

    public void setUsers(Set<UserSummaryDTO> users) {
        this.users = users;
    }

    public void setPointsOfSale(Set<POSSummaryDTO> pointsOfSale) {
        this.pointsOfSale = pointsOfSale;
    }

    public void setSectors(Set<SectorSummaryDTO> sectors) {
        this.sectors = sectors;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class EnterpriseResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private String logo;
        private String primaryColor;
        private String secondaryColor;
        private UserSummaryDTO enterpriseAdmin;
        private Set<UserSummaryDTO> users;
        private Set<POSSummaryDTO> pointsOfSale;
        private Set<SectorSummaryDTO> sectors;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        EnterpriseResponseBuilder() {
        }

        public EnterpriseResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public EnterpriseResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EnterpriseResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public EnterpriseResponseBuilder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public EnterpriseResponseBuilder primaryColor(String primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public EnterpriseResponseBuilder secondaryColor(String secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

        public EnterpriseResponseBuilder enterpriseAdmin(UserSummaryDTO enterpriseAdmin) {
            this.enterpriseAdmin = enterpriseAdmin;
            return this;
        }

        public EnterpriseResponseBuilder users(Set<UserSummaryDTO> users) {
            this.users = users;
            return this;
        }

        public EnterpriseResponseBuilder pointsOfSale(Set<POSSummaryDTO> pointsOfSale) {
            this.pointsOfSale = pointsOfSale;
            return this;
        }

        public EnterpriseResponseBuilder sectors(Set<SectorSummaryDTO> sectors) {
            this.sectors = sectors;
            return this;
        }

        public EnterpriseResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EnterpriseResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public EnterpriseResponse build() {
            return new EnterpriseResponse(this.id, this.name, this.description, this.logo, this.primaryColor, this.secondaryColor, this.enterpriseAdmin, this.users, this.pointsOfSale, this.sectors, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "EnterpriseResponse.EnterpriseResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", logo=" + this.logo + ", primaryColor=" + this.primaryColor + ", secondaryColor=" + this.secondaryColor + ", enterpriseAdmin=" + this.enterpriseAdmin + ", users=" + this.users + ", pointsOfSale=" + this.pointsOfSale + ", sectors=" + this.sectors + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}

