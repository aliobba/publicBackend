package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateFormComponentRequest {
    @NotNull
    private Integer id;
    private String elementType;
    private String label;
    private Integer orderIndex;
    private Boolean required;
    private Integer formId;
    private List<ComponentPropertyRequest> properties;
    private List<ElementOptionRequest> options;

    public UpdateFormComponentRequest(@NotNull Integer id, String elementType, String label, Integer orderIndex, Boolean required, Integer formId, List<ComponentPropertyRequest> properties, List<ElementOptionRequest> options) {
        this.id = id;
        this.elementType = elementType;
        this.label = label;
        this.orderIndex = orderIndex;
        this.required = required;
        this.formId = formId;
        this.properties = properties;
        this.options = options;
    }

    public UpdateFormComponentRequest() {
    }

    public static UpdateFormComponentRequestBuilder builder() {
        return new UpdateFormComponentRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateFormComponentRequest)) return false;
        final UpdateFormComponentRequest other = (UpdateFormComponentRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$elementType = this.getElementType();
        final Object other$elementType = other.getElementType();
        if (this$elementType == null ? other$elementType != null : !this$elementType.equals(other$elementType))
            return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$orderIndex = this.getOrderIndex();
        final Object other$orderIndex = other.getOrderIndex();
        if (this$orderIndex == null ? other$orderIndex != null : !this$orderIndex.equals(other$orderIndex))
            return false;
        final Object this$required = this.getRequired();
        final Object other$required = other.getRequired();
        if (this$required == null ? other$required != null : !this$required.equals(other$required)) return false;
        final Object this$formId = this.getFormId();
        final Object other$formId = other.getFormId();
        if (this$formId == null ? other$formId != null : !this$formId.equals(other$formId)) return false;
        final Object this$properties = this.getProperties();
        final Object other$properties = other.getProperties();
        if (this$properties == null ? other$properties != null : !this$properties.equals(other$properties))
            return false;
        final Object this$options = this.getOptions();
        final Object other$options = other.getOptions();
        if (this$options == null ? other$options != null : !this$options.equals(other$options)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateFormComponentRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $elementType = this.getElementType();
        result = result * PRIME + ($elementType == null ? 43 : $elementType.hashCode());
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $orderIndex = this.getOrderIndex();
        result = result * PRIME + ($orderIndex == null ? 43 : $orderIndex.hashCode());
        final Object $required = this.getRequired();
        result = result * PRIME + ($required == null ? 43 : $required.hashCode());
        final Object $formId = this.getFormId();
        result = result * PRIME + ($formId == null ? 43 : $formId.hashCode());
        final Object $properties = this.getProperties();
        result = result * PRIME + ($properties == null ? 43 : $properties.hashCode());
        final Object $options = this.getOptions();
        result = result * PRIME + ($options == null ? 43 : $options.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateFormComponentRequest(id=" + this.getId() + ", elementType=" + this.getElementType() + ", label=" + this.getLabel() + ", orderIndex=" + this.getOrderIndex() + ", required=" + this.getRequired() + ", formId=" + this.getFormId() + ", properties=" + this.getProperties() + ", options=" + this.getOptions() + ")";
    }

    public @NotNull Integer getId() {
        return this.id;
    }

    public String getElementType() {
        return this.elementType;
    }

    public String getLabel() {
        return this.label;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public Integer getFormId() {
        return this.formId;
    }

    public List<ComponentPropertyRequest> getProperties() {
        return this.properties;
    }

    public List<ElementOptionRequest> getOptions() {
        return this.options;
    }

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public void setProperties(List<ComponentPropertyRequest> properties) {
        this.properties = properties;
    }

    public void setOptions(List<ElementOptionRequest> options) {
        this.options = options;
    }

    public static class UpdateFormComponentRequestBuilder {
        private @NotNull Integer id;
        private String elementType;
        private String label;
        private Integer orderIndex;
        private Boolean required;
        private Integer formId;
        private List<ComponentPropertyRequest> properties;
        private List<ElementOptionRequest> options;

        UpdateFormComponentRequestBuilder() {
        }

        public UpdateFormComponentRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdateFormComponentRequestBuilder elementType(String elementType) {
            this.elementType = elementType;
            return this;
        }

        public UpdateFormComponentRequestBuilder label(String label) {
            this.label = label;
            return this;
        }

        public UpdateFormComponentRequestBuilder orderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
            return this;
        }

        public UpdateFormComponentRequestBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public UpdateFormComponentRequestBuilder formId(Integer formId) {
            this.formId = formId;
            return this;
        }

        public UpdateFormComponentRequestBuilder properties(List<ComponentPropertyRequest> properties) {
            this.properties = properties;
            return this;
        }

        public UpdateFormComponentRequestBuilder options(List<ElementOptionRequest> options) {
            this.options = options;
            return this;
        }

        public UpdateFormComponentRequest build() {
            return new UpdateFormComponentRequest(this.id, this.elementType, this.label, this.orderIndex, this.required, this.formId, this.properties, this.options);
        }

        public String toString() {
            return "UpdateFormComponentRequest.UpdateFormComponentRequestBuilder(id=" + this.id + ", elementType=" + this.elementType + ", label=" + this.label + ", orderIndex=" + this.orderIndex + ", required=" + this.required + ", formId=" + this.formId + ", properties=" + this.properties + ", options=" + this.options + ")";
        }
    }
}