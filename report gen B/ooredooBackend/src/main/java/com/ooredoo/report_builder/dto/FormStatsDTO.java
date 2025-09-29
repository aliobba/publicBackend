package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;
import java.util.List;


public class FormStatsDTO {
    private Integer formId;
    private String formName;
    private Long submissionCount;
    private Long componentCount;
    private LocalDateTime lastSubmissionAt;
    private LocalDateTime createdAt;
    private String creatorName;
    private List<String> topSubmitters;

    // Constructors
    public FormStatsDTO() {
    }

    public FormStatsDTO(Integer formId, String formName, Long submissionCount, Long componentCount) {
        this.formId = formId;
        this.formName = formName;
        this.submissionCount = submissionCount;
        this.componentCount = componentCount;
    }

    public FormStatsDTO(Integer formId, String formName, Long submissionCount, Long componentCount, LocalDateTime lastSubmissionAt, LocalDateTime createdAt, String creatorName, List<String> topSubmitters) {
        this.formId = formId;
        this.formName = formName;
        this.submissionCount = submissionCount;
        this.componentCount = componentCount;
        this.lastSubmissionAt = lastSubmissionAt;
        this.createdAt = createdAt;
        this.creatorName = creatorName;
        this.topSubmitters = topSubmitters;
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

    public Long getSubmissionCount() {
        return this.submissionCount;
    }

    public void setSubmissionCount(Long submissionCount) {
        this.submissionCount = submissionCount;
    }

    public Long getComponentCount() {
        return this.componentCount;
    }

    public void setComponentCount(Long componentCount) {
        this.componentCount = componentCount;
    }

    public LocalDateTime getLastSubmissionAt() {
        return this.lastSubmissionAt;
    }

    public void setLastSubmissionAt(LocalDateTime lastSubmissionAt) {
        this.lastSubmissionAt = lastSubmissionAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<String> getTopSubmitters() {
        return this.topSubmitters;
    }

    public void setTopSubmitters(List<String> topSubmitters) {
        this.topSubmitters = topSubmitters;
    }
}
