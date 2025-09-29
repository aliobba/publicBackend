package com.ooredoo.report_builder.dto;

import java.util.HashMap;
import java.util.Map;

public class FormSubmissionRequestDTO {
    private Integer formId;

    // FIXED: Use assignmentId instead of componentId
    // Map: assignmentId → value (as string)
    private Map<Integer, String> assignmentValues;

    // Constructors
    public FormSubmissionRequestDTO() {
        this.assignmentValues = new HashMap<>();
    }

    public FormSubmissionRequestDTO(Integer formId, Map<Integer, String> assignmentValues) {
        this.formId = formId;
        this.assignmentValues = assignmentValues != null ? assignmentValues : new HashMap<>();
    }

    // Helper method to add assignment value
    public void addAssignmentValue(Integer assignmentId, String value) {
        if (this.assignmentValues == null) {
            this.assignmentValues = new HashMap<>();
        }
        this.assignmentValues.put(assignmentId, value);
    }

    // Getters and Setters
    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Map<Integer, String> getAssignmentValues() {
        return assignmentValues;
    }

    public void setAssignmentValues(Map<Integer, String> assignmentValues) {
        this.assignmentValues = assignmentValues;
    }
}
