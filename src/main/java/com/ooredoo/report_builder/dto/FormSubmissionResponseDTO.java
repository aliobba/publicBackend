package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FormSubmissionResponseDTO {
    private Integer id;
    private Integer formId;
    private String formName;
    private Integer submittedById;
    private String submittedByName;
    private LocalDateTime submittedAt;
    private Boolean isComplete;
    private List<SubmissionValueDTO> values;

    // Additional metadata
    private Integer valueCount;
    private Boolean canEdit;
    private Boolean canDelete;

    // Constructors

    public FormSubmissionResponseDTO() {
        this.values = new ArrayList<>();
    }

    public FormSubmissionResponseDTO(Integer id, Integer formId, String formName) {
        this.id = id;
        this.formId = formId;
        this.formName = formName;
        this.values = new ArrayList<>();
    }

    public FormSubmissionResponseDTO(Integer id, Integer formId, String formName, Integer submittedById, String submittedByName, LocalDateTime submittedAt, Boolean isComplete, List<SubmissionValueDTO> values, Integer valueCount, Boolean canEdit, Boolean canDelete) {
        this.id = id;
        this.formId = formId;
        this.formName = formName;
        this.submittedById = submittedById;
        this.submittedByName = submittedByName;
        this.submittedAt = submittedAt;
        this.isComplete = isComplete;
        this.values = values;
        this.valueCount = valueCount;
        this.canEdit = canEdit;
        this.canDelete = canDelete;
    }

    // Helper method
    public void addValue(SubmissionValueDTO value) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        this.values.add(value);
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

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Integer getSubmittedById() {
        return this.submittedById;
    }

    public void setSubmittedById(Integer submittedById) {
        this.submittedById = submittedById;
    }

    public String getSubmittedByName() {
        return this.submittedByName;
    }

    public void setSubmittedByName(String submittedByName) {
        this.submittedByName = submittedByName;
    }

    public LocalDateTime getSubmittedAt() {
        return this.submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public List<SubmissionValueDTO> getValues() {
        return this.values;
    }

    public void setValues(List<SubmissionValueDTO> values) {
        this.values = values;
    }

    public Integer getValueCount() {
        return this.valueCount;
    }

    public void setValueCount(Integer valueCount) {
        this.valueCount = valueCount;
    }

    public Boolean getCanEdit() {
        return this.canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getCanDelete() {
        return this.canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }
}
