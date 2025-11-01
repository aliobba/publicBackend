package com.ooredoo.report_builder.dto;

import com.ooredoo.report_builder.entity.FormComponent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class FormResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer creatorId;
    private List<FormComponent> components;
    private Set<Integer> assignedUserIds;

    FormResponseDTO(Integer id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Integer creatorId, List<FormComponent> components, Set<Integer> assignedUserIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creatorId = creatorId;
        this.components = components;
        this.assignedUserIds = assignedUserIds;
    }

    public FormResponseDTO() {
    }

    public static FormResponseDTOBuilder builder() {
        return new FormResponseDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public List<FormComponent> getComponents() {
        return this.components;
    }

    public void setComponents(List<FormComponent> components) {
        this.components = components;
    }

    public Set<Integer> getAssignedUserIds() {
        return this.assignedUserIds;
    }

    public void setAssignedUserIds(Set<Integer> assignedUserIds) {
        this.assignedUserIds = assignedUserIds;
    }

    public static class FormResponseDTOBuilder {
        private Integer id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Integer creatorId;
        private List<FormComponent> components;
        private Set<Integer> assignedUserIds;

        FormResponseDTOBuilder() {
        }

        public FormResponseDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FormResponseDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FormResponseDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FormResponseDTOBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FormResponseDTOBuilder creatorId(Integer creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public FormResponseDTOBuilder components(List<FormComponent> components) {
            this.components = components;
            return this;
        }

        public FormResponseDTOBuilder assignedUserIds(Set<Integer> assignedUserIds) {
            this.assignedUserIds = assignedUserIds;
            return this;
        }

        public FormResponseDTO build() {
            return new FormResponseDTO(this.id, this.name, this.description, this.createdAt, this.updatedAt, this.creatorId, this.components, this.assignedUserIds);
        }

        public String toString() {
            return "FormResponseDTO.FormResponseDTOBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", creatorId=" + this.creatorId + ", components=" + this.components + ", assignedUserIds=" + this.assignedUserIds + ")";
        }
    }
}
