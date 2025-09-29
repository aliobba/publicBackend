package com.ooredoo.report_builder.dto;


import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class FormComponentDTO {

    private Integer id;
    private ComponentType elementType;
    private String label;
    private Boolean required;
    private Integer orderIndex;
    private List<ComponentPropertyDTO> properties;
    private List<ElementOptionDTO> options;
    private Integer formId;
    private Boolean isGlobal;
    private User createdBy;
    private List<FormComponentAssignment> formAssignments;
    private LocalDateTime createdAt;

    public FormComponentDTO(ComponentType elementType,
                            String label, Boolean required,
                            Integer orderIndex, List<ComponentPropertyDTO> properties,
                            List<ElementOptionDTO> options, Integer formId, Boolean isGlobal,
                            User createdBy, List<FormComponentAssignment> formAssignments,
                            LocalDateTime createdAt) {
        this.elementType = elementType;
        this.label = label;
        this.required = required;
        this.orderIndex = orderIndex;
        this.properties = properties;
        this.options = options;
        this.formId = formId;
        this.isGlobal = isGlobal;
        this.createdBy = createdBy;
        this.formAssignments = formAssignments;
        this.createdAt = createdAt;
    }

    public FormComponentDTO(Integer id, ComponentType elementType, String label, Boolean required, Integer orderIndex, List<ComponentPropertyDTO> properties, List<ElementOptionDTO> options, Integer formId, Boolean isGlobal, User createdBy, List<FormComponentAssignment> formAssignments) {
        this.id = id;
        this.elementType = elementType;
        this.label = label;
        this.required = required;
        this.orderIndex = orderIndex;
        this.properties = properties;
        this.options = options;
        this.formId = formId;
        this.isGlobal = isGlobal;
        this.createdBy = createdBy;
        this.formAssignments = formAssignments;
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


    public FormComponentDTO() {
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static FormComponentDTOBuilder builder() {
        return new FormComponentDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public ComponentType getElementType() {
        return this.elementType;
    }

    public String getLabel() {
        return this.label;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setElementType(ComponentType elementType) {
        this.elementType = elementType;
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

    public List<ComponentPropertyDTO> getProperties() {
        return this.properties;
    }

    public List<ElementOptionDTO> getOptions() {
        return this.options;
    }

    public Boolean getIsGlobal() {
        return this.isGlobal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setProperties(List<ComponentPropertyDTO> properties) {
        this.properties = properties;
    }

    public void setOptions(List<ElementOptionDTO> options) {
        this.options = options;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<FormComponentAssignment> getFormAssignments() {
        return this.formAssignments;
    }

    public void setFormAssignments(List<FormComponentAssignment> formAssignments) {
        this.formAssignments = formAssignments;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public static class FormComponentDTOBuilder {
        private Integer id;
        private ComponentType elementType;
        private String label;
        private Boolean required;
        private Integer orderIndex;
        private List<ComponentPropertyDTO> properties;
        private List<ElementOptionDTO> options;
        private Integer formId;
        private Boolean isGlobal;
        private User createdBy;
        private List<FormComponentAssignment> formAssignments;

        FormComponentDTOBuilder() {
        }

        public FormComponentDTOBuilder id(Integer id) {
            this.id = id;
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

        public FormComponentDTOBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public FormComponentDTOBuilder formAssignments(List<FormComponentAssignment> formAssignments) {
            this.formAssignments = formAssignments;
            return this;
        }

        public FormComponentDTO build() {
            return new FormComponentDTO(this.id, this.elementType, this.label, this.required, this.orderIndex, this.properties, this.options, this.formId, this.isGlobal, this.createdBy, this.formAssignments);
        }

        public String toString() {
            return "FormComponentDTO.FormComponentDTOBuilder(id=" + this.id + ", elementType=" + this.elementType + ", label=" + this.label + ", required=" + this.required + ", orderIndex=" + this.orderIndex + ", properties=" + this.properties + ", options=" + this.options + ", formId=" + this.formId + ", isGlobal=" + this.isGlobal + ", createdBy=" + this.createdBy + ", formAssignments=" + this.formAssignments + ")";
        }
    }
}
