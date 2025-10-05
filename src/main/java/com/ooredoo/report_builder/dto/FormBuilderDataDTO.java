package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FormBuilderDataDTO {

    private FormResponseDTO form;
    private List<ComponentTemplateDTO> availableComponentTypes;
    private List<FormComponentDTO> reusableComponents;
    private List<FormComponentAssignmentDTO> formAssignments;

    // Form statistics
    private Long submissionCount;
    private LocalDateTime lastSubmissionAt;


    public FormBuilderDataDTO(FormResponseDTO form, List<ComponentTemplateDTO> availableComponentTypes) {
        this.form = form;
        this.availableComponentTypes = availableComponentTypes;
    }

    public FormBuilderDataDTO(FormResponseDTO form, List<ComponentTemplateDTO> availableComponentTypes, List<FormComponentDTO> reusableComponents, List<FormComponentAssignmentDTO> formAssignments, Long submissionCount, LocalDateTime lastSubmissionAt) {
        this.form = form;
        this.availableComponentTypes = availableComponentTypes;
        this.reusableComponents = reusableComponents;
        this.formAssignments = formAssignments;
        this.submissionCount = submissionCount;
        this.lastSubmissionAt = lastSubmissionAt;
    }

    public FormBuilderDataDTO() {
    }

    public FormResponseDTO getForm() {
        return this.form;
    }

    public void setForm(FormResponseDTO form) {
        this.form = form;
    }

    public List<ComponentTemplateDTO> getAvailableComponentTypes() {
        return this.availableComponentTypes;
    }

    public void setAvailableComponentTypes(List<ComponentTemplateDTO> availableComponentTypes) {
        this.availableComponentTypes = availableComponentTypes;
    }

    public List<FormComponentDTO> getReusableComponents() {
        return this.reusableComponents;
    }

    public void setReusableComponents(List<FormComponentDTO> reusableComponents) {
        this.reusableComponents = reusableComponents;
    }

    public List<FormComponentAssignmentDTO> getFormAssignments() {
        return this.formAssignments;
    }

    public void setFormAssignments(List<FormComponentAssignmentDTO> formAssignments) {
        this.formAssignments = formAssignments;
    }

    public Long getSubmissionCount() {
        return this.submissionCount;
    }

    public void setSubmissionCount(Long submissionCount) {
        this.submissionCount = submissionCount;
    }

    public LocalDateTime getLastSubmissionAt() {
        return this.lastSubmissionAt;
    }

    public void setLastSubmissionAt(LocalDateTime lastSubmissionAt) {
        this.lastSubmissionAt = lastSubmissionAt;
    }
}
