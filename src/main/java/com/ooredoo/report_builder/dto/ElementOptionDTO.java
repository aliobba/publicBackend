package com.ooredoo.report_builder.dto;


public class ElementOptionDTO {
    private Integer id;
    private String label;
    private String value;
    private Integer displayOrder;
    private Integer componentId;

    public ElementOptionDTO(Integer id, String label, String value, Integer displayOrder, Integer componentId) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.displayOrder = displayOrder;
        this.componentId = componentId;
    }

    public ElementOptionDTO() {
    }

    public ElementOptionDTO(String label, String value, Integer displayOrder) {
        this.label = label;
        this.value = value;
        this.displayOrder = displayOrder;
    }


    public static ElementOptionDTOBuilder builder() {
        return new ElementOptionDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public static class ElementOptionDTOBuilder {
        private Integer id;
        private String label;
        private String value;
        private Integer displayOrder;
        private Integer componentId;

        ElementOptionDTOBuilder() {
        }

        public ElementOptionDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ElementOptionDTOBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ElementOptionDTOBuilder value(String value) {
            this.value = value;
            return this;
        }

        public ElementOptionDTOBuilder displayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
            return this;
        }

        public ElementOptionDTOBuilder componentId(Integer componentId) {
            this.componentId = componentId;
            return this;
        }

        public ElementOptionDTO build() {
            return new ElementOptionDTO(this.id, this.label, this.value, this.displayOrder, this.componentId);
        }

        public String toString() {
            return "ElementOptionDTO.ElementOptionDTOBuilder(id=" + this.id + ", label=" + this.label + ", value=" + this.value + ", displayOrder=" + this.displayOrder + ", componentId=" + this.componentId + ")";
        }
    }
}
