package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class AnimatorResponse {
    private Integer id;
    private String pin;
    private String description;
    private AnimatorRoleSummaryDTO role;
    private POSSummaryDTO pos;
    private Set<UserSummaryDTO> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnimatorResponse(Integer id, String pin, String description, AnimatorRoleSummaryDTO role, POSSummaryDTO pos, Set<UserSummaryDTO> users, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pin = pin;
        this.description = description;
        this.role = role;
        this.pos = pos;
        this.users = users;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AnimatorResponse() {
    }

    public static AnimatorResponseBuilder builder() {
        return new AnimatorResponseBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getPin() {
        return this.pin;
    }

    public String getDescription() {
        return this.description;
    }

    public AnimatorRoleSummaryDTO getRole() {
        return this.role;
    }

    public POSSummaryDTO getPos() {
        return this.pos;
    }

    public Set<UserSummaryDTO> getUsers() {
        return this.users;
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

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRole(AnimatorRoleSummaryDTO role) {
        this.role = role;
    }

    public void setPos(POSSummaryDTO pos) {
        this.pos = pos;
    }

    public void setUsers(Set<UserSummaryDTO> users) {
        this.users = users;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class AnimatorResponseBuilder {
        private Integer id;
        private String pin;
        private String description;
        private AnimatorRoleSummaryDTO role;
        private POSSummaryDTO pos;
        private Set<UserSummaryDTO> users;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        AnimatorResponseBuilder() {
        }

        public AnimatorResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AnimatorResponseBuilder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public AnimatorResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AnimatorResponseBuilder role(AnimatorRoleSummaryDTO role) {
            this.role = role;
            return this;
        }

        public AnimatorResponseBuilder pos(POSSummaryDTO pos) {
            this.pos = pos;
            return this;
        }

        public AnimatorResponseBuilder users(Set<UserSummaryDTO> users) {
            this.users = users;
            return this;
        }

        public AnimatorResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AnimatorResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AnimatorResponse build() {
            return new AnimatorResponse(this.id, this.pin, this.description, this.role, this.pos, this.users, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "AnimatorResponse.AnimatorResponseBuilder(id=" + this.id + ", pin=" + this.pin + ", description=" + this.description + ", role=" + this.role + ", pos=" + this.pos + ", users=" + this.users + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}

