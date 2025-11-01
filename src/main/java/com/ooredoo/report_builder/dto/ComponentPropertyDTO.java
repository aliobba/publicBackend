package com.ooredoo.report_builder.dto;


public class ComponentPropertyDTO {

    private Integer id;
    private String propertyName;
    private String propertyValue;
    private Integer componentId;

    public ComponentPropertyDTO(Integer id, String propertyName, String propertyValue, Integer componentId) {
        this.id = id;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.componentId = componentId;
    }

    public ComponentPropertyDTO() {
    }

    public static ComponentPropertyDTOBuilder builder() {
        return new ComponentPropertyDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public static class ComponentPropertyDTOBuilder {
        private Integer id;
        private String propertyName;
        private String propertyValue;
        private Integer componentId;

        ComponentPropertyDTOBuilder() {
        }

        public ComponentPropertyDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ComponentPropertyDTOBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public ComponentPropertyDTOBuilder propertyValue(String propertyValue) {
            this.propertyValue = propertyValue;
            return this;
        }

        public ComponentPropertyDTOBuilder componentId(Integer componentId) {
            this.componentId = componentId;
            return this;
        }

        public ComponentPropertyDTO build() {
            return new ComponentPropertyDTO(this.id, this.propertyName, this.propertyValue, this.componentId);
        }

        public String toString() {
            return "ComponentPropertyDTO.ComponentPropertyDTOBuilder(id=" + this.id + ", propertyName=" + this.propertyName + ", propertyValue=" + this.propertyValue + ", componentId=" + this.componentId + ")";
        }
    }
}
