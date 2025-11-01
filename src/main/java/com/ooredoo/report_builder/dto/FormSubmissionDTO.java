package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FormSubmissionDTO {

    private Integer id;
    private LocalDateTime submittedAt;
    private Integer formId; // Only expose the form's ID
    private Integer submittedById;
    private List<SubmissionValueDTO> values;


    public FormSubmissionDTO() {
    }

    public FormSubmissionDTO(Integer id, LocalDateTime submittedAt, Integer formId, Integer submittedById, List<SubmissionValueDTO> values) {
        this.id = id;
        this.submittedAt = submittedAt;
        this.formId = formId;
        this.submittedById = submittedById;
        this.values = values;
    }

    public static FormSubmissionDTOBuilder builder() {
        return new FormSubmissionDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSubmittedAt() {
        return this.submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Integer getFormId() {
        return this.formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getSubmittedById() {
        return this.submittedById;
    }

    public void setSubmittedById(Integer submittedById) {
        this.submittedById = submittedById;
    }

    public List<SubmissionValueDTO> getValues() {
        return this.values;
    }

    public void setValues(List<SubmissionValueDTO> values) {
        this.values = values;
    }


    public static class FormSubmissionDTOBuilder {
        private Integer id;
        private LocalDateTime submittedAt;
        private Integer formId;
        private Integer submittedById;
        private List<SubmissionValueDTO> values;

        FormSubmissionDTOBuilder() {
        }

        public FormSubmissionDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormSubmissionDTOBuilder submittedAt(LocalDateTime submittedAt) {
            this.submittedAt = submittedAt;
            return this;
        }

        public FormSubmissionDTOBuilder formId(Integer formId) {
            this.formId = formId;
            return this;
        }

        public FormSubmissionDTOBuilder submittedById(Integer submittedById) {
            this.submittedById = submittedById;
            return this;
        }

        public FormSubmissionDTOBuilder values(List<SubmissionValueDTO> values) {
            this.values = values;
            return this;
        }

        public FormSubmissionDTO build() {
            return new FormSubmissionDTO(this.id, this.submittedAt, this.formId, this.submittedById, this.values);
        }

        public String toString() {
            return "FormSubmissionDTO.FormSubmissionDTOBuilder(id=" + this.id + ", submittedAt=" + this.submittedAt + ", formId=" + this.formId + ", submittedById=" + this.submittedById + ", values=" + this.values + ")";
        }
    }
}
