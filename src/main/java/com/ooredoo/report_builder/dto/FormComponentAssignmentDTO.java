package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;

public class FormComponentAssignmentDTO {
    private Integer id;
    private Integer formId;
    private Integer componentId;
    private Integer orderIndex;
    private Boolean isActive;
    private LocalDateTime assignedAt;
    private LocalDateTime unassignedAt;

    // Include component details for convenience
    private FormComponentDTO component;
    private String formName;

    // Constructors
    public FormComponentAssignmentDTO() {
    }

    public FormComponentAssignmentDTO(Integer formId, Integer componentId, Integer orderIndex) {
        this.formId = formId;
        this.componentId = componentId;
        this.orderIndex = orderIndex;
        this.isActive = true;
    }

    public FormComponentAssignmentDTO(Integer id, Integer formId, Integer componentId, Integer orderIndex, Boolean isActive, LocalDateTime assignedAt, LocalDateTime unassignedAt, FormComponentDTO component, String formName) {
        this.id = id;
        this.formId = formId;
        this.componentId = componentId;
        this.orderIndex = orderIndex;
        this.isActive = isActive;
        this.assignedAt = assignedAt;
        this.unassignedAt = unassignedAt;
        this.component = component;
        this.formName = formName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormId() {
        return this.formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getAssignedAt() {
        return this.assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public LocalDateTime getUnassignedAt() {
        return this.unassignedAt;
    }

    public void setUnassignedAt(LocalDateTime unassignedAt) {
        this.unassignedAt = unassignedAt;
    }

    public FormComponentDTO getComponent() {
        return this.component;
    }

    public void setComponent(FormComponentDTO component) {
        this.component = component;
    }

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
