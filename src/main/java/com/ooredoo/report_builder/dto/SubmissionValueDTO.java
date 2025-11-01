package com.ooredoo.report_builder.dto;


public class SubmissionValueDTO {

    private Integer id;
    private String value;
    private Integer submissionId;

    // FIXED: Include assignment info instead of just component
    private Integer assignmentId;
    private Integer componentId;
    private String componentLabel;
    private String componentType;
    private Integer orderIndex;

    // Additional metadata for UI
    private Boolean isRequired;
    private String displayValue;

    public SubmissionValueDTO(Integer assignmentId, String value) {
        this.assignmentId = assignmentId;
        this.value = value;
    }

    public SubmissionValueDTO(Integer id, String value, Integer assignmentId, String componentLabel) {
        this.id = id;
        this.value = value;
        this.assignmentId = assignmentId;
        this.componentLabel = componentLabel;
    }

    public SubmissionValueDTO(Integer id, String value, Integer submissionId, Integer assignmentId, Integer componentId, String componentLabel, String componentType, Integer orderIndex, Boolean isRequired, String displayValue) {
        this.id = id;
        this.value = value;
        this.submissionId = submissionId;
        this.assignmentId = assignmentId;
        this.componentId = componentId;
        this.componentLabel = componentLabel;
        this.componentType = componentType;
        this.orderIndex = orderIndex;
        this.isRequired = isRequired;
        this.displayValue = displayValue;
    }

    public SubmissionValueDTO() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSubmissionId() {
        return this.submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getAssignmentId() {
        return this.assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public String getComponentLabel() {
        return this.componentLabel;
    }

    public void setComponentLabel(String componentLabel) {
        this.componentLabel = componentLabel;
    }

    public String getComponentType() {
        return this.componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getIsRequired() {
        return this.isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
