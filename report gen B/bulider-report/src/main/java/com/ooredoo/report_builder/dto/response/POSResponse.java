package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class POSResponse {
    private Integer id;
    private String name;
    private String description;
    private String location;
    private UserSummaryDTO manager;
    private Set<UserSummaryDTO> users;
    private EnterpriseSummaryDTO enterprise;
    private RegionSummaryDTO region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public POSResponse(Integer id, String name, String description, String location, UserSummaryDTO manager, Set<UserSummaryDTO> users, EnterpriseSummaryDTO enterprise, RegionSummaryDTO region, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.manager = manager;
        this.users = users;
        this.enterprise = enterprise;
        this.region = region;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public POSResponse() {
    }

    public static POSResponseBuilder builder() {
        return new POSResponseBuilder();
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

    public String getLocation() {
        return this.location;
    }

    public UserSummaryDTO getManager() {
        return this.manager;
    }

    public Set<UserSummaryDTO> getUsers() {
        return this.users;
    }

    public EnterpriseSummaryDTO getEnterprise() {
        return this.enterprise;
    }

    public RegionSummaryDTO getRegion() {
        return this.region;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setManager(UserSummaryDTO manager) {
        this.manager = manager;
    }

    public void setUsers(Set<UserSummaryDTO> users) {
        this.users = users;
    }

    public void setEnterprise(EnterpriseSummaryDTO enterprise) {
        this.enterprise = enterprise;
    }

    public void setRegion(RegionSummaryDTO region) {
        this.region = region;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class POSResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private String location;
        private UserSummaryDTO manager;
        private Set<UserSummaryDTO> users;
        private EnterpriseSummaryDTO enterprise;
        private RegionSummaryDTO region;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        POSResponseBuilder() {
        }

        public POSResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public POSResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public POSResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public POSResponseBuilder location(String location) {
            this.location = location;
            return this;
        }

        public POSResponseBuilder manager(UserSummaryDTO manager) {
            this.manager = manager;
            return this;
        }

        public POSResponseBuilder users(Set<UserSummaryDTO> users) {
            this.users = users;
            return this;
        }

        public POSResponseBuilder enterprise(EnterpriseSummaryDTO enterprise) {
            this.enterprise = enterprise;
            return this;
        }

        public POSResponseBuilder region(RegionSummaryDTO region) {
            this.region = region;
            return this;
        }

        public POSResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public POSResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public POSResponse build() {
            return new POSResponse(this.id, this.name, this.description, this.location, this.manager, this.users, this.enterprise, this.region, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "POSResponse.POSResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", location=" + this.location + ", manager=" + this.manager + ", users=" + this.users + ", enterprise=" + this.enterprise + ", region=" + this.region + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}

