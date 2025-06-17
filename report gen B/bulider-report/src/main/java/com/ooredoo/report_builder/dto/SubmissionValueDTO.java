package com.ooredoo.report_builder.dto;

public class SubmissionValueDTO {

    private Integer id;
    private String value;
    private Integer submissionId; // Only expose the submission's ID
    private Integer componentId;

    public SubmissionValueDTO(Integer id, String value, Integer submissionId, Integer componentId) {
        this.id = id;
        this.value = value;
        this.submissionId = submissionId;
        this.componentId = componentId;
    }

    public SubmissionValueDTO() {
    }

    public static SubmissionValueDTOBuilder builder() {
        return new SubmissionValueDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getSubmissionId() {
        return this.submissionId;
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

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public static class SubmissionValueDTOBuilder {
        private Integer id;
        private String value;
        private Integer submissionId;
        private Integer componentId;

        SubmissionValueDTOBuilder() {
        }

        public SubmissionValueDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SubmissionValueDTOBuilder value(String value) {
            this.value = value;
            return this;
        }

        public SubmissionValueDTOBuilder submissionId(Integer submissionId) {
            this.submissionId = submissionId;
            return this;
        }

        public SubmissionValueDTOBuilder componentId(Integer componentId) {
            this.componentId = componentId;
            return this;
        }

        public SubmissionValueDTO build() {
            return new SubmissionValueDTO(this.id, this.value, this.submissionId, this.componentId);
        }

        public String toString() {
            return "SubmissionValueDTO.SubmissionValueDTOBuilder(id=" + this.id + ", value=" + this.value + ", submissionId=" + this.submissionId + ", componentId=" + this.componentId + ")";
        }
    }
}
