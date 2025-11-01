package com.ooredoo.report_builder.dto;

import java.util.List;

public class ComponentUsageStatsDTO {
    private Integer componentId;
    private String componentLabel;
    private String componentType;
    private Long usageCount;
    private Long submissionCount;
    private List<String> usedInForms;


    public ComponentUsageStatsDTO(Integer componentId, String componentLabel, String componentType, Long usageCount) {
        this.componentId = componentId;
        this.componentLabel = componentLabel;
        this.componentType = componentType;
        this.usageCount = usageCount;
    }

    public ComponentUsageStatsDTO(Integer componentId, String componentLabel, String componentType, Long usageCount, Long submissionCount, List<String> usedInForms) {
        this.componentId = componentId;
        this.componentLabel = componentLabel;
        this.componentType = componentType;
        this.usageCount = usageCount;
        this.submissionCount = submissionCount;
        this.usedInForms = usedInForms;
    }

    public ComponentUsageStatsDTO() {
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

    public Long getUsageCount() {
        return this.usageCount;
    }

    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }

    public Long getSubmissionCount() {
        return this.submissionCount;
    }

    public void setSubmissionCount(Long submissionCount) {
        this.submissionCount = submissionCount;
    }

    public List<String> getUsedInForms() {
        return this.usedInForms;
    }

    public void setUsedInForms(List<String> usedInForms) {
        this.usedInForms = usedInForms;
    }
}
