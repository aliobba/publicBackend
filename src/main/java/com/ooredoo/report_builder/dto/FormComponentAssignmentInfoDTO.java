package com.ooredoo.report_builder.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormComponentAssignmentInfoDTO {

    private Integer assignmentId;
    private Integer componentId;
    private String componentType;
    private String label;
    private Boolean required;
    private Integer orderIndex;
    private List<ComponentPropertyDTO> properties;
    private List<ElementOptionDTO> options;

    // Additional metadata for UI
    private String placeholder;
    private String helpText;
    private Map<String, String> validationRules;

    // Constructors
    public FormComponentAssignmentInfoDTO() {
        this.properties = new ArrayList<>();
        this.options = new ArrayList<>();
        this.validationRules = new HashMap<>();
    }

    public FormComponentAssignmentInfoDTO(Integer assignmentId, Integer componentId, String componentType, String label) {
        this.assignmentId = assignmentId;
        this.componentId = componentId;
        this.componentType = componentType;
        this.label = label;
        this.properties = new ArrayList<>();
        this.options = new ArrayList<>();
        this.validationRules = new HashMap<>();
    }

    public FormComponentAssignmentInfoDTO(Integer assignmentId, Integer componentId, String componentType, String label, Boolean required, Integer orderIndex, List<ComponentPropertyDTO> properties, List<ElementOptionDTO> options, String placeholder, String helpText, Map<String, String> validationRules) {
        this.assignmentId = assignmentId;
        this.componentId = componentId;
        this.componentType = componentType;
        this.label = label;
        this.required = required;
        this.orderIndex = orderIndex;
        this.properties = properties;
        this.options = options;
        this.placeholder = placeholder;
        this.helpText = helpText;
        this.validationRules = validationRules;
    }

    // Helper methods
    public void addProperty(ComponentPropertyDTO property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(property);
    }

    public void addOption(ElementOptionDTO option) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        this.options.add(option);
    }

    public void addValidationRule(String rule, String value) {
        if (this.validationRules == null) {
            this.validationRules = new HashMap<>();
        }
        this.validationRules.put(rule, value);
    }

    public String getPropertyValue(String propertyName) {
        if (properties == null) return null;
        return properties.stream()
                .filter(p -> p.getPropertyName().equals(propertyName))
                .map(ComponentPropertyDTO::getPropertyValue)
                .findFirst()
                .orElse(null);
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

    public String getComponentType() {
        return this.componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public List<ComponentPropertyDTO> getProperties() {
        return this.properties;
    }

    public void setProperties(List<ComponentPropertyDTO> properties) {
        this.properties = properties;
    }

    public List<ElementOptionDTO> getOptions() {
        return this.options;
    }

    public void setOptions(List<ElementOptionDTO> options) {
        this.options = options;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getHelpText() {
        return this.helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public Map<String, String> getValidationRules() {
        return this.validationRules;
    }

    public void setValidationRules(Map<String, String> validationRules) {
        this.validationRules = validationRules;
    }
}
