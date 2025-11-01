package com.ooredoo.report_builder.dto;


import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.enums.ComponentType;

import java.time.LocalDateTime;
import java.util.List;

public class FormComponentDTO {

    private Integer id;
    private Integer assignmentId; // ID of the FormComponentAssignment
    private ComponentType elementType;
    private String label;
    private Boolean required;
    private String placeholder;
    private Integer orderIndex;
    private List<ComponentPropertyDTO> properties;
    private List<ElementOptionDTO> options;
    private Integer formId;
    private Boolean isGlobal;
    private Integer createdById;
    private String createdByName;
    private List<FormComponentAssignment> formAssignments;
    private LocalDateTime createdAt;
    private Boolean isActive;

    public FormComponentDTO(Integer id, Integer assignmentId, ComponentType elementType, String label, Boolean required, String placeholder, Integer orderIndex, List<ComponentPropertyDTO> properties, List<ElementOptionDTO> options, Integer formId, Boolean isGlobal, Integer createdById, String createdByName, List<FormComponentAssignment> formAssignments, LocalDateTime createdAt, Boolean isActive) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.elementType = elementType;
        this.label = label;
        this.required = required;
        this.placeholder = placeholder;
        this.orderIndex = orderIndex;
        this.properties = properties;
        this.options = options;
        this.formId = formId;
        this.isGlobal = isGlobal;
        this.createdById = createdById;
        this.createdByName = createdByName;
        this.formAssignments = formAssignments;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public FormComponentDTO(Integer id, Integer assignmentId, ComponentType elementType, String label, Boolean required, String placeholder, Integer orderIndex, List<ComponentPropertyDTO> properties, List<ElementOptionDTO> options, Integer formId, Boolean isGlobal, Integer createdById, String createdByName, List<FormComponentAssignment> formAssignments) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.elementType = elementType;
        this.label = label;
        this.required = required;
        this.placeholder = placeholder;
        this.orderIndex = orderIndex;
        this.properties = properties;
        this.options = options;
        this.formId = formId;
        this.isGlobal = isGlobal;
        this.createdById = createdById;
        this.createdByName = createdByName;
        this.formAssignments = formAssignments;
    }

    public FormComponentDTO() {
    }

    public static FormComponentDTOBuilder builder() {
        return new FormComponentDTOBuilder();
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignmentId() {
        return this.assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public ComponentType getElementType() {
        return this.elementType;
    }

    public void setElementType(ComponentType elementType) {
        this.elementType = elementType;
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

    public String getPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getFormId() {
        return this.formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
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

    public Boolean getIsGlobal() {
        return this.isGlobal;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public Integer getCreatedById() {
        return this.createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public List<FormComponentAssignment> getFormAssignments() {
        return this.formAssignments;
    }

    public void setFormAssignments(List<FormComponentAssignment> formAssignments) {
        this.formAssignments = formAssignments;
    }

    public static class FormComponentDTOBuilder {
        private Integer id;
        private Integer assignmentId;
        private ComponentType elementType;
        private String label;
        private Boolean required;
        private String placeholder;
        private Integer orderIndex;
        private List<ComponentPropertyDTO> properties;
        private List<ElementOptionDTO> options;
        private Integer formId;
        private Boolean isGlobal;
        private Integer createdById;
        private String createdByName;
        private List<FormComponentAssignment> formAssignments;
        private LocalDateTime createdAt;
        private Boolean isActive;

        FormComponentDTOBuilder() {
        }

        public FormComponentDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormComponentDTOBuilder assignmentId(Integer assignmentId) {
            this.assignmentId = assignmentId;
            return this;
        }

        public FormComponentDTOBuilder elementType(ComponentType elementType) {
            this.elementType = elementType;
            return this;
        }

        public FormComponentDTOBuilder label(String label) {
            this.label = label;
            return this;
        }

        public FormComponentDTOBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public FormComponentDTOBuilder placeholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public FormComponentDTOBuilder orderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
            return this;
        }

        public FormComponentDTOBuilder properties(List<ComponentPropertyDTO> properties) {
            this.properties = properties;
            return this;
        }

        public FormComponentDTOBuilder options(List<ElementOptionDTO> options) {
            this.options = options;
            return this;
        }

        public FormComponentDTOBuilder formId(Integer formId) {
            this.formId = formId;
            return this;
        }

        public FormComponentDTOBuilder isGlobal(Boolean isGlobal) {
            this.isGlobal = isGlobal;
            return this;
        }

        public FormComponentDTOBuilder createdById(Integer createdById) {
            this.createdById = createdById;
            return this;
        }

        public FormComponentDTOBuilder createdByName(String createdByName) {
            this.createdByName = createdByName;
            return this;
        }

        public FormComponentDTOBuilder formAssignments(List<FormComponentAssignment> formAssignments) {
            this.formAssignments = formAssignments;
            return this;
        }

        public FormComponentDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FormComponentDTOBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public FormComponentDTO build() {
            return new FormComponentDTO(this.id, this.assignmentId, this.elementType, this.label, this.required, this.placeholder, this.orderIndex, this.properties, this.options, this.formId, this.isGlobal, this.createdById, this.createdByName, this.formAssignments, this.createdAt, this.isActive);
        }

        public String toString() {
            return "FormComponentDTO.FormComponentDTOBuilder(id=" + this.id + ", assignmentId=" + this.assignmentId + ", elementType=" + this.elementType + ", label=" + this.label + ", required=" + this.required + ", placeholder=" + this.placeholder + ", orderIndex=" + this.orderIndex + ", properties=" + this.properties + ", options=" + this.options + ", formId=" + this.formId + ", isGlobal=" + this.isGlobal + ", createdById=" + this.createdById + ", createdByName=" + this.createdByName + ", formAssignments=" + this.formAssignments + ", createdAt=" + this.createdAt + ", isActive=" + this.isActive + ")";
        }
    }
}