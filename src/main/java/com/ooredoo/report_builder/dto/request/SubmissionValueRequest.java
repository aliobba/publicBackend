package com.ooredoo.report_builder.dto.request;

public class SubmissionValueRequest {
    private Integer id;
    private String value;
    private Integer componentId;

    public SubmissionValueRequest(Integer id, String value, Integer componentId) {
        this.id = id;
        this.value = value;
        this.componentId = componentId;
    }

    public SubmissionValueRequest() {
    }

    public static SubmissionValueRequestBuilder builder() {
        return new SubmissionValueRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SubmissionValueRequest)) return false;
        final SubmissionValueRequest other = (SubmissionValueRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$componentId = this.getComponentId();
        final Object other$componentId = other.getComponentId();
        if (this$componentId == null ? other$componentId != null : !this$componentId.equals(other$componentId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SubmissionValueRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $componentId = this.getComponentId();
        result = result * PRIME + ($componentId == null ? 43 : $componentId.hashCode());
        return result;
    }

    public String toString() {
        return "SubmissionValueRequest(id=" + this.getId() + ", value=" + this.getValue() + ", componentId=" + this.getComponentId() + ")";
    }

    public Integer getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public static class SubmissionValueRequestBuilder {
        private Integer id;
        private String value;
        private Integer componentId;

        SubmissionValueRequestBuilder() {
        }

        public SubmissionValueRequestBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SubmissionValueRequestBuilder value(String value) {
            this.value = value;
            return this;
        }

        public SubmissionValueRequestBuilder componentId(Integer componentId) {
            this.componentId = componentId;
            return this;
        }

        public SubmissionValueRequest build() {
            return new SubmissionValueRequest(this.id, this.value, this.componentId);
        }

        public String toString() {
            return "SubmissionValueRequest.SubmissionValueRequestBuilder(id=" + this.id + ", value=" + this.value + ", componentId=" + this.componentId + ")";
        }
    }
}