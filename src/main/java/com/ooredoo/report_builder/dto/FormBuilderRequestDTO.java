package com.ooredoo.report_builder.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormBuilderRequestDTO {

    @NotBlank(message = "Form name is required")
    @Size(min = 3, max = 100, message = "Form name must be between 3 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Valid
    private List<FormComponentDTO> components;

    private Set<Integer> assignedUserIds;

    // Additional options
    private Boolean createWithDefaults;
    private Boolean assignToCreator;

    // Constructors
    public FormBuilderRequestDTO() {
        this.components = new ArrayList<>();
        this.assignedUserIds = new HashSet<>();
        this.createWithDefaults = true;
        this.assignToCreator = false;
    }

    public FormBuilderRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
        this.components = new ArrayList<>();
        this.assignedUserIds = new HashSet<>();
        this.createWithDefaults = true;
        this.assignToCreator = false;
    }

    public FormBuilderRequestDTO(@NotBlank(message = "Form name is required") @Size(min = 3, max = 100, message = "Form name must be between 3 and 100 characters") String name, @Size(max = 500, message = "Description cannot exceed 500 characters") String description, @Valid List<FormComponentDTO> components, Set<Integer> assignedUserIds, Boolean createWithDefaults, Boolean assignToCreator) {
        this.name = name;
        this.description = description;
        this.components = components;
        this.assignedUserIds = assignedUserIds;
        this.createWithDefaults = createWithDefaults;
        this.assignToCreator = assignToCreator;
    }

    // Helper methods
    public void addComponent(FormComponentDTO component) {
        if (this.components == null) {
            this.components = new ArrayList<>();
        }
        this.components.add(component);
    }

    public void addAssignedUser(Integer userId) {
        if (this.assignedUserIds == null) {
            this.assignedUserIds = new HashSet<>();
        }
        this.assignedUserIds.add(userId);
    }

    public @NotBlank(message = "Form name is required") @Size(min = 3, max = 100, message = "Form name must be between 3 and 100 characters") String getName() {
        return this.name;
    }

    public void setName(@NotBlank(message = "Form name is required") @Size(min = 3, max = 100, message = "Form name must be between 3 and 100 characters") String name) {
        this.name = name;
    }

    public @Size(max = 500, message = "Description cannot exceed 500 characters") String getDescription() {
        return this.description;
    }

    public void setDescription(@Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
        this.description = description;
    }

    public @Valid List<FormComponentDTO> getComponents() {
        return this.components;
    }

    public void setComponents(@Valid List<FormComponentDTO> components) {
        this.components = components;
    }

    public Set<Integer> getAssignedUserIds() {
        return this.assignedUserIds;
    }

    public void setAssignedUserIds(Set<Integer> assignedUserIds) {
        this.assignedUserIds = assignedUserIds;
    }

    public Boolean getCreateWithDefaults() {
        return this.createWithDefaults;
    }

    public void setCreateWithDefaults(Boolean createWithDefaults) {
        this.createWithDefaults = createWithDefaults;
    }

    public Boolean getAssignToCreator() {
        return this.assignToCreator;
    }

    public void setAssignToCreator(Boolean assignToCreator) {
        this.assignToCreator = assignToCreator;
    }
}
