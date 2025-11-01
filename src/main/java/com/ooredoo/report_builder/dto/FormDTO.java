package com.ooredoo.report_builder.dto;

import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class FormDTO {


    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer creatorId;
    private List<FormComponent> components;
    private List<FormSubmission> submissions;
    private Set<User> assignedUsers;


    public FormDTO() {
    }

    public FormDTO(Integer id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Integer creatorId, List<FormComponent> components, List<FormSubmission> submissions, Set<User> assignedUsers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creatorId = creatorId;
        this.components = components;
        this.submissions = submissions;
        this.assignedUsers = assignedUsers;
    }

    public static FormDTOBuilder builder() {
        return new FormDTOBuilder();
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

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public Set<User> getAssignedUsers() {
        return this.assignedUsers;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public static class FormDTOBuilder {
        private Integer id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Integer creatorId;
        private List<FormComponent> components;
        private List<FormSubmission> submissions;
        private Set<User> assignedUsers;

        FormDTOBuilder() {
        }

        public FormDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FormDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FormDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FormDTOBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FormDTOBuilder creatorId(Integer creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public String toString() {
            return "FormDTO.FormDTOBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", creatorId=" + this.creatorId + ")";
        }

        public FormDTOBuilder components(List<FormComponent> components) {
            this.components = components;
            return this;
        }

        public FormDTOBuilder submissions(List<FormSubmission> submissions) {
            this.submissions = submissions;
            return this;
        }

        public FormDTOBuilder assignedUsers(Set<User> assignedUsers) {
            this.assignedUsers = assignedUsers;
            return this;
        }

        public FormDTO build() {
            return new FormDTO(this.id, this.name, this.description, this.createdAt, this.updatedAt, this.creatorId, this.components, this.submissions, this.assignedUsers);
        }
    }
}
