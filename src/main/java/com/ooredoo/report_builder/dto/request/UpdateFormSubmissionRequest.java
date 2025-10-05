package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateFormSubmissionRequest {
    @NotNull
    private Integer id;
    private LocalDateTime submittedAt;
    private Integer formId;
    private Integer submittedById;
    private List<SubmissionValueRequest> values;

    public UpdateFormSubmissionRequest(@NotNull Integer id, LocalDateTime submittedAt, Integer formId, Integer submittedById, List<SubmissionValueRequest> values) {
        this.id = id;
        this.submittedAt = submittedAt;
        this.formId = formId;
        this.submittedById = submittedById;
        this.values = values;
    }

    public UpdateFormSubmissionRequest() {
    }

    public static UpdateFormSubmissionRequestBuilder builder() {
        return new UpdateFormSubmissionRequestBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateFormSubmissionRequest)) return false;
        final UpdateFormSubmissionRequest other = (UpdateFormSubmissionRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$submittedAt = this.getSubmittedAt();
        final Object other$submittedAt = other.getSubmittedAt();
        if (this$submittedAt == null ? other$submittedAt != null : !this$submittedAt.equals(other$submittedAt))
            return false;
        final Object this$formId = this.getFormId();
        final Object other$formId = other.getFormId();
        if (this$formId == null ? other$formId != null : !this$formId.equals(other$formId)) return false;
        final Object this$submittedById = this.getSubmittedById();
        final Object other$submittedById = other.getSubmittedById();
        if (this$submittedById == null ? other$submittedById != null : !this$submittedById.equals(other$submittedById))
            return false;
        final Object this$values = this.getValues();
        final Object other$values = other.getValues();
        if (this$values == null ? other$values != null : !this$values.equals(other$values)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateFormSubmissionRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $submittedAt = this.getSubmittedAt();
        result = result * PRIME + ($submittedAt == null ? 43 : $submittedAt.hashCode());
        final Object $formId = this.getFormId();
        result = result * PRIME + ($formId == null ? 43 : $formId.hashCode());
        final Object $submittedById = this.getSubmittedById();
        result = result * PRIME + ($submittedById == null ? 43 : $submittedById.hashCode());
        final Object $values = this.getValues();
        result = result * PRIME + ($values == null ? 43 : $values.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateFormSubmissionRequest(id=" + this.getId() + ", submittedAt=" + this.getSubmittedAt() + ", formId=" + this.getFormId() + ", submittedById=" + this.getSubmittedById() + ", values=" + this.getValues() + ")";
    }

    public @NotNull Integer getId() {
        return this.id;
    }

    public LocalDateTime getSubmittedAt() {
        return this.submittedAt;
    }

    public Integer getFormId() {
        return this.formId;
    }

    public Integer getSubmittedById() {
        return this.submittedById;
    }

    public List<SubmissionValueRequest> getValues() {
        return this.values;
    }

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public void setSubmittedById(Integer submittedById) {
        this.submittedById = submittedById;
    }

    public void setValues(List<SubmissionValueRequest> values) {
        this.values = values;
    }

    public static class UpdateFormSubmissionRequestBuilder {
        private @NotNull Integer id;
        private LocalDateTime submittedAt;
        private Integer formId;
        private Integer submittedById;
        private List<SubmissionValueRequest> values;

        UpdateFormSubmissionRequestBuilder() {
        }

        public UpdateFormSubmissionRequestBuilder id(@NotNull Integer id) {
            this.id = id;
            return this;
        }

        public UpdateFormSubmissionRequestBuilder submittedAt(LocalDateTime submittedAt) {
            this.submittedAt = submittedAt;
            return this;
        }

        public UpdateFormSubmissionRequestBuilder formId(Integer formId) {
            this.formId = formId;
            return this;
        }

        public UpdateFormSubmissionRequestBuilder submittedById(Integer submittedById) {
            this.submittedById = submittedById;
            return this;
        }

        public UpdateFormSubmissionRequestBuilder values(List<SubmissionValueRequest> values) {
            this.values = values;
            return this;
        }

        public UpdateFormSubmissionRequest build() {
            return new UpdateFormSubmissionRequest(this.id, this.submittedAt, this.formId, this.submittedById, this.values);
        }

        public String toString() {
            return "UpdateFormSubmissionRequest.UpdateFormSubmissionRequestBuilder(id=" + this.id + ", submittedAt=" + this.submittedAt + ", formId=" + this.formId + ", submittedById=" + this.submittedById + ", values=" + this.values + ")";
        }
    }
}