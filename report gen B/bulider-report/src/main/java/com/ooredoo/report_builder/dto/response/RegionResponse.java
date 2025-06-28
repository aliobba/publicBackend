package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;


public class RegionResponse {
    private Integer id;
    private String name;
    private String description;
    private UserSummaryDTO regionHead;
    private Set<UserSummaryDTO> users;
    private ZoneSummaryDTO zone;
    private Set<POSSummaryDTO> pointsOfSale;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RegionResponse(Integer id, String name, String description, UserSummaryDTO regionHead, Set<UserSummaryDTO> users, ZoneSummaryDTO zone, Set<POSSummaryDTO> pointsOfSale, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.regionHead = regionHead;
        this.users = users;
        this.zone = zone;
        this.pointsOfSale = pointsOfSale;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RegionResponse() {
    }

    public static RegionResponseBuilder builder() {
        return new RegionResponseBuilder();
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

    public UserSummaryDTO getRegionHead() {
        return this.regionHead;
    }

    public Set<UserSummaryDTO> getUsers() {
        return this.users;
    }

    public ZoneSummaryDTO getZone() {
        return this.zone;
    }

    public Set<POSSummaryDTO> getPointsOfSale() {
        return this.pointsOfSale;
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

    public void setRegionHead(UserSummaryDTO regionHead) {
        this.regionHead = regionHead;
    }

    public void setUsers(Set<UserSummaryDTO> users) {
        this.users = users;
    }

    public void setZone(ZoneSummaryDTO zone) {
        this.zone = zone;
    }

    public void setPointsOfSale(Set<POSSummaryDTO> pointsOfSale) {
        this.pointsOfSale = pointsOfSale;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class RegionResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private UserSummaryDTO regionHead;
        private Set<UserSummaryDTO> users;
        private ZoneSummaryDTO zone;
        private Set<POSSummaryDTO> pointsOfSale;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        RegionResponseBuilder() {
        }

        public RegionResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RegionResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RegionResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RegionResponseBuilder regionHead(UserSummaryDTO regionHead) {
            this.regionHead = regionHead;
            return this;
        }

        public RegionResponseBuilder users(Set<UserSummaryDTO> users) {
            this.users = users;
            return this;
        }

        public RegionResponseBuilder zone(ZoneSummaryDTO zone) {
            this.zone = zone;
            return this;
        }

        public RegionResponseBuilder pointsOfSale(Set<POSSummaryDTO> pointsOfSale) {
            this.pointsOfSale = pointsOfSale;
            return this;
        }

        public RegionResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RegionResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RegionResponse build() {
            return new RegionResponse(this.id, this.name, this.description, this.regionHead, this.users, this.zone, this.pointsOfSale, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "RegionResponse.RegionResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", regionHead=" + this.regionHead + ", users=" + this.users + ", zone=" + this.zone + ", pointsOfSale=" + this.pointsOfSale + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}