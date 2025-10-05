package com.ooredoo.report_builder.dto.request;

public class ElementOptionRequest {
    private Integer id;
    private String label;
    private String value;
    private Integer displayOrder;

    public ElementOptionRequest(Integer id, String label, String value, Integer displayOrder) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.displayOrder = displayOrder;
    }

    public ElementOptionRequest() {
    }

    public static ElementOptionRequestBuilder builder() {
        return new ElementOptionRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ElementOptionRequest)) return false;
        final ElementOptionRequest other = (ElementOptionRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$displayOrder = this.getDisplayOrder();
        final Object other$displayOrder = other.getDisplayOrder();
        if (this$displayOrder == null ? other$displayOrder != null : !this$displayOrder.equals(other$displayOrder))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ElementOptionRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $displayOrder = this.getDisplayOrder();
        result = result * PRIME + ($displayOrder == null ? 43 : $displayOrder.hashCode());
        return result;
    }

    public String toString() {
        return "ElementOptionRequest(id=" + this.getId() + ", label=" + this.getLabel() + ", value=" + this.getValue() + ", displayOrder=" + this.getDisplayOrder() + ")";
    }

    public Integer getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public static class ElementOptionRequestBuilder {
        private Integer id;
        private String label;
        private String value;
        private Integer displayOrder;

        ElementOptionRequestBuilder() {
        }

        public ElementOptionRequestBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ElementOptionRequestBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ElementOptionRequestBuilder value(String value) {
            this.value = value;
            return this;
        }

        public ElementOptionRequestBuilder displayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
            return this;
        }

        public ElementOptionRequest build() {
            return new ElementOptionRequest(this.id, this.label, this.value, this.displayOrder);
        }

        public String toString() {
            return "ElementOptionRequest.ElementOptionRequestBuilder(id=" + this.id + ", label=" + this.label + ", value=" + this.value + ", displayOrder=" + this.displayOrder + ")";
        }
    }
}