package com.ooredoo.report_builder.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentTemplateDTO {
    private String elementType;
    private String displayName;
    private String description;
    private String iconClass;
    private Map<String, String> defaultProperties;
    private List<ElementOptionDTO> defaultOptions;
    private Boolean requiresOptions;
    private Boolean supportsFileUpload;

    // UI metadata
    private String category; // e.g., "Input", "Selection", "Date/Time", "File"
    private Integer sortOrder;
    private Boolean isAdvanced;

    // Constructors
    public ComponentTemplateDTO() {
        this.defaultProperties = new HashMap<>();
        this.defaultOptions = new ArrayList<>();
    }

    public ComponentTemplateDTO(String elementType, String displayName, String description) {
        this.elementType = elementType;
        this.displayName = displayName;
        this.description = description;
        this.defaultProperties = new HashMap<>();
        this.defaultOptions = new ArrayList<>();
    }

    public ComponentTemplateDTO(String elementType, String displayName, String description, String iconClass, Map<String, String> defaultProperties, List<ElementOptionDTO> defaultOptions, Boolean requiresOptions, Boolean supportsFileUpload, String category, Integer sortOrder, Boolean isAdvanced) {
        this.elementType = elementType;
        this.displayName = displayName;
        this.description = description;
        this.iconClass = iconClass;
        this.defaultProperties = defaultProperties;
        this.defaultOptions = defaultOptions;
        this.requiresOptions = requiresOptions;
        this.supportsFileUpload = supportsFileUpload;
        this.category = category;
        this.sortOrder = sortOrder;
        this.isAdvanced = isAdvanced;
    }

    // Helper methods
    public void addDefaultProperty(String name, String value) {
        if (this.defaultProperties == null) {
            this.defaultProperties = new HashMap<>();
        }
        this.defaultProperties.put(name, value);
    }

    public void addDefaultOption(ElementOptionDTO option) {
        if (this.defaultOptions == null) {
            this.defaultOptions = new ArrayList<>();
        }
        this.defaultOptions.add(option);
    }

    public String getElementType() {
        return this.elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconClass() {
        return this.iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public Map<String, String> getDefaultProperties() {
        return this.defaultProperties;
    }

    public void setDefaultProperties(Map<String, String> defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    public List<ElementOptionDTO> getDefaultOptions() {
        return this.defaultOptions;
    }

    public void setDefaultOptions(List<ElementOptionDTO> defaultOptions) {
        this.defaultOptions = defaultOptions;
    }

    public Boolean getRequiresOptions() {
        return this.requiresOptions;
    }

    public void setRequiresOptions(Boolean requiresOptions) {
        this.requiresOptions = requiresOptions;
    }

    public Boolean getSupportsFileUpload() {
        return this.supportsFileUpload;
    }

    public void setSupportsFileUpload(Boolean supportsFileUpload) {
        this.supportsFileUpload = supportsFileUpload;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsAdvanced() {
        return this.isAdvanced;
    }

    public void setIsAdvanced(Boolean isAdvanced) {
        this.isAdvanced = isAdvanced;
    }
}
