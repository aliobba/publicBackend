package com.ooredoo.report_builder.dto.request;

public class ComponentPropertyRequest {
    private Integer id;
    private String propertyName;
    private String propertyValue;

    public ComponentPropertyRequest(Integer id, String propertyName, String propertyValue) {
        this.id = id;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public ComponentPropertyRequest() {
    }

    public static ComponentPropertyRequestBuilder builder() {
        return new ComponentPropertyRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ComponentPropertyRequest)) return false;
        final ComponentPropertyRequest other = (ComponentPropertyRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$propertyName = this.getPropertyName();
        final Object other$propertyName = other.getPropertyName();
        if (this$propertyName == null ? other$propertyName != null : !this$propertyName.equals(other$propertyName))
            return false;
        final Object this$propertyValue = this.getPropertyValue();
        final Object other$propertyValue = other.getPropertyValue();
        if (this$propertyValue == null ? other$propertyValue != null : !this$propertyValue.equals(other$propertyValue))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ComponentPropertyRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $propertyName = this.getPropertyName();
        result = result * PRIME + ($propertyName == null ? 43 : $propertyName.hashCode());
        final Object $propertyValue = this.getPropertyValue();
        result = result * PRIME + ($propertyValue == null ? 43 : $propertyValue.hashCode());
        return result;
    }

    public String toString() {
        return "ComponentPropertyRequest(id=" + this.getId() + ", propertyName=" + this.getPropertyName() + ", propertyValue=" + this.getPropertyValue() + ")";
    }

    public Integer getId() {
        return this.id;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public static class ComponentPropertyRequestBuilder {
        private Integer id;
        private String propertyName;
        private String propertyValue;

        ComponentPropertyRequestBuilder() {
        }

        public ComponentPropertyRequestBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ComponentPropertyRequestBuilder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public ComponentPropertyRequestBuilder propertyValue(String propertyValue) {
            this.propertyValue = propertyValue;
            return this;
        }

        public ComponentPropertyRequest build() {
            return new ComponentPropertyRequest(this.id, this.propertyName, this.propertyValue);
        }

        public String toString() {
            return "ComponentPropertyRequest.ComponentPropertyRequestBuilder(id=" + this.id + ", propertyName=" + this.propertyName + ", propertyValue=" + this.propertyValue + ")";
        }
    }
}