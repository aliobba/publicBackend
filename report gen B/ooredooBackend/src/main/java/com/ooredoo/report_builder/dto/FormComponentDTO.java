package com.ooredoo.report_builder.dto;


import java.util.List;

public class FormComponentDTO {

    private Integer id;
    private String elementType;
    private String label;
    private Boolean required;
    private List<ComponentPropertyDTO> properties;
    private List<ElementOptionDTO> options;
    private Integer formId;


    public FormComponentDTO() {
    }

    public static FormComponentDTOBuilder builder() {
        return new FormComponentDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getElementType() {
        return this.elementType;
    }

    public String getLabel() {
        return this.label;
    }


    public Boolean getRequired() {
        return this.required;
    }

    public Integer getFormId() {
        return this.formId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public List<ComponentPropertyDTO> getProperties() {
        return this.properties;
    }

    public List<ElementOptionDTO> getOptions() {
        return this.options;
    }

    public void setProperties(List<ComponentPropertyDTO> properties) {
        this.properties = properties;
    }

    public void setOptions(List<ElementOptionDTO> options) {
        this.options = options;
    }

    public static class FormComponentDTOBuilder {
        private Integer id;
        private String elementType;
        private String label;
        private Boolean required;
        private Integer formId;
        private List<ComponentPropertyDTO> properties;
        private List<ElementOptionDTO> options;

        FormComponentDTOBuilder() {
        }

        public FormComponentDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormComponentDTOBuilder elementType(String elementType) {
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

        public FormComponentDTOBuilder formId(Integer formId) {
            this.formId = formId;
            return this;
        }


        public String toString() {
            return "FormComponentDTO.FormComponentDTOBuilder(id=" + this.id + ", elementType=" + this.elementType + ", label=" + this.label + ", required=" + this.required + ", formId=" + this.formId + ")";
        }

        public FormComponentDTOBuilder properties(List<ComponentPropertyDTO> properties) {
            this.properties = properties;
            return this;
        }

        public FormComponentDTOBuilder options(List<ElementOptionDTO> options) {
            this.options = options;
            return this;
        }
    }
}
