package com.ooredoo.report_builder.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class AnimatorRoleResponse {
    private Integer id;
    private String name;
    private String description;
    private Set<RoleActionSummaryDTO> actions;
    private Set<AnimatorSummaryDTO> animators;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnimatorRoleResponse(Integer id, String name, String description, Set<RoleActionSummaryDTO> actions, Set<AnimatorSummaryDTO> animators, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.actions = actions;
        this.animators = animators;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AnimatorRoleResponse() {
    }

    public static AnimatorRoleResponseBuilder builder() {
        return new AnimatorRoleResponseBuilder();
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

    public Set<RoleActionSummaryDTO> getActions() {
        return this.actions;
    }

    public Set<AnimatorSummaryDTO> getAnimators() {
        return this.animators;
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

    public void setActions(Set<RoleActionSummaryDTO> actions) {
        this.actions = actions;
    }

    public void setAnimators(Set<AnimatorSummaryDTO> animators) {
        this.animators = animators;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class AnimatorRoleResponseBuilder {
        private Integer id;
        private String name;
        private String description;
        private Set<RoleActionSummaryDTO> actions;
        private Set<AnimatorSummaryDTO> animators;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        AnimatorRoleResponseBuilder() {
        }

        public AnimatorRoleResponseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AnimatorRoleResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AnimatorRoleResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AnimatorRoleResponseBuilder actions(Set<RoleActionSummaryDTO> actions) {
            this.actions = actions;
            return this;
        }

        public AnimatorRoleResponseBuilder animators(Set<AnimatorSummaryDTO> animators) {
            this.animators = animators;
            return this;
        }

        public AnimatorRoleResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AnimatorRoleResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AnimatorRoleResponse build() {
            return new AnimatorRoleResponse(this.id, this.name, this.description, this.actions, this.animators, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "AnimatorRoleResponse.AnimatorRoleResponseBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", actions=" + this.actions + ", animators=" + this.animators + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}

