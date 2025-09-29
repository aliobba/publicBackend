package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormWithAssignmentsDTO {
    private Integer id;
    private String name;
    private String description;
    private List<FormComponentAssignmentInfoDTO> componentAssignments;

    // Additional form metadata
    private String creatorName;
    private LocalDateTime createdAt;
    private Boolean canSubmit;
    private Integer totalComponents;

    // Constructors
    public FormWithAssignmentsDTO() {
        this.componentAssignments = new ArrayList<>();
    }

    public FormWithAssignmentsDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.componentAssignments = new ArrayList<>();
    }

    public FormWithAssignmentsDTO(Integer id, String name, String description, List<FormComponentAssignmentInfoDTO> componentAssignments, String creatorName, LocalDateTime createdAt, Boolean canSubmit, Integer totalComponents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.componentAssignments = componentAssignments;
        this.creatorName = creatorName;
        this.createdAt = createdAt;
        this.canSubmit = canSubmit;
        this.totalComponents = totalComponents;
    }

    // Helper methods
    public void addComponentAssignment(FormComponentAssignmentInfoDTO assignment) {
        if (this.componentAssignments == null) {
            this.componentAssignments = new ArrayList<>();
        }
        this.componentAssignments.add(assignment);
    }

    public List<FormComponentAssignmentInfoDTO> getRequiredAssignments() {
        if (componentAssignments == null) {
            return new ArrayList<>();
        }
        return componentAssignments.stream()
                .filter(FormComponentAssignmentInfoDTO::getRequired)
                .collect(Collectors.toList());
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

    public List<FormComponentAssignmentInfoDTO> getComponentAssignments() {
        return this.componentAssignments;
    }

    public void setComponentAssignments(List<FormComponentAssignmentInfoDTO> componentAssignments) {
        this.componentAssignments = componentAssignments;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getCanSubmit() {
        return this.canSubmit;
    }

    public void setCanSubmit(Boolean canSubmit) {
        this.canSubmit = canSubmit;
    }

    public Integer getTotalComponents() {
        return this.totalComponents;
    }

    public void setTotalComponents(Integer totalComponents) {
        this.totalComponents = totalComponents;
    }
}
