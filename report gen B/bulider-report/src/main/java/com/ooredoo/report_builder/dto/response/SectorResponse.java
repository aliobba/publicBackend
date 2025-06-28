package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;


public class SectorResponse {
    private Integer id;
    private String name;
    private String description;
    private UserSummaryDTO sectorHead;
    private Set<UserSummaryDTO> users;
    private EnterpriseSummaryDTO enterprise;
    private Set<ZoneSummaryDTO> zones;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SectorResponse(Integer id, String name, String description, UserSummaryDTO sectorHead, Set<UserSummaryDTO> users, EnterpriseSummaryDTO enterprise, Set<ZoneSummaryDTO> zones, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectorHead = sectorHead;
        this.users = users;
        this.enterprise = enterprise;
        this.zones = zones;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SectorResponse() {
    }

    public static SectorResponseBuilder builder() {
        return new SectorResponseBuilder();
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

    public UserSummaryDTO getSectorHead() {
        return this.sectorHead;
    }

    public Set<UserSummaryDTO> getUsers() {
        return this.users;
    }

    public EnterpriseSummaryDTO getEnterprise() {
        return this.enterprise;
    }

    public Set<ZoneSummaryDTO> getZones() {
        return this.zones;
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

    public void setSectorHead(UserSummaryDTO sectorHead) {
        this.sectorHead = sectorHead;
    }

    public void setUsers(Set<UserSummaryDTO> users) {
        this.users = users;
    }

    public void setEnterprise(EnterpriseSummaryDTO enterprise) {
        this.enterprise = enterprise;
    }

    public void setZones(Set<ZoneSummaryDTO> zones) {
        this.zones = zones;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class SectorResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private UserSummaryDTO sectorHead;
        private Set<UserSummaryDTO> users;
        private EnterpriseSummaryDTO enterprise;
        private Set<ZoneSummaryDTO> zones;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        SectorResponseBuilder() {
        }

        public SectorResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SectorResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SectorResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SectorResponseBuilder sectorHead(UserSummaryDTO sectorHead) {
            this.sectorHead = sectorHead;
            return this;
        }

        public SectorResponseBuilder users(Set<UserSummaryDTO> users) {
            this.users = users;
            return this;
        }

        public SectorResponseBuilder enterprise(EnterpriseSummaryDTO enterprise) {
            this.enterprise = enterprise;
            return this;
        }

        public SectorResponseBuilder zones(Set<ZoneSummaryDTO> zones) {
            this.zones = zones;
            return this;
        }

        public SectorResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SectorResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public SectorResponse build() {
            return new SectorResponse(this.id, this.name, this.description, this.sectorHead, this.users, this.enterprise, this.zones, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "SectorResponse.SectorResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", sectorHead=" + this.sectorHead + ", users=" + this.users + ", enterprise=" + this.enterprise + ", zones=" + this.zones + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}

