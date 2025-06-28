package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class ZoneResponse {
    private Integer id;
    private String name;
    private String description;
    private UserSummaryDTO zoneHead;
    private Set<UserSummaryDTO> users;
    private SectorSummaryDTO sector;
    private Set<RegionSummaryDTO> regions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ZoneResponse(Integer id, String name, String description, UserSummaryDTO zoneHead, Set<UserSummaryDTO> users, SectorSummaryDTO sector, Set<RegionSummaryDTO> regions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.zoneHead = zoneHead;
        this.users = users;
        this.sector = sector;
        this.regions = regions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ZoneResponse() {
    }

    public static ZoneResponseBuilder builder() {
        return new ZoneResponseBuilder();
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

    public UserSummaryDTO getZoneHead() {
        return this.zoneHead;
    }

    public Set<UserSummaryDTO> getUsers() {
        return this.users;
    }

    public SectorSummaryDTO getSector() {
        return this.sector;
    }

    public Set<RegionSummaryDTO> getRegions() {
        return this.regions;
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

    public void setZoneHead(UserSummaryDTO zoneHead) {
        this.zoneHead = zoneHead;
    }

    public void setUsers(Set<UserSummaryDTO> users) {
        this.users = users;
    }

    public void setSector(SectorSummaryDTO sector) {
        this.sector = sector;
    }

    public void setRegions(Set<RegionSummaryDTO> regions) {
        this.regions = regions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class ZoneResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private UserSummaryDTO zoneHead;
        private Set<UserSummaryDTO> users;
        private SectorSummaryDTO sector;
        private Set<RegionSummaryDTO> regions;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        ZoneResponseBuilder() {
        }

        public ZoneResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ZoneResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ZoneResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ZoneResponseBuilder zoneHead(UserSummaryDTO zoneHead) {
            this.zoneHead = zoneHead;
            return this;
        }

        public ZoneResponseBuilder users(Set<UserSummaryDTO> users) {
            this.users = users;
            return this;
        }

        public ZoneResponseBuilder sector(SectorSummaryDTO sector) {
            this.sector = sector;
            return this;
        }

        public ZoneResponseBuilder regions(Set<RegionSummaryDTO> regions) {
            this.regions = regions;
            return this;
        }

        public ZoneResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ZoneResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ZoneResponse build() {
            return new ZoneResponse(this.id, this.name, this.description, this.zoneHead, this.users, this.sector, this.regions, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "ZoneResponse.ZoneResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", zoneHead=" + this.zoneHead + ", users=" + this.users + ", sector=" + this.sector + ", regions=" + this.regions + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}