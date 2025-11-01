package com.ooredoo.report_builder.dto;


public class FormRequestDTO {

    private String name;
    private String description;
    private Integer creatorId;

    public FormRequestDTO(String name, String description, Integer creatorId) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
    }

    public FormRequestDTO() {
    }

    public static FormRequestDTOBuilder builder() {
        return new FormRequestDTOBuilder();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public static class FormRequestDTOBuilder {
        private String name;
        private String description;
        private Integer creatorId;

        FormRequestDTOBuilder() {
        }

        public FormRequestDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FormRequestDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FormRequestDTOBuilder creatorId(Integer creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public FormRequestDTO build() {
            return new FormRequestDTO(this.name, this.description, this.creatorId);
        }

        public String toString() {
            return "FormRequestDTO.FormRequestDTOBuilder(name=" + this.name + ", description=" + this.description + ", creatorId=" + this.creatorId + ")";
        }
    }
}
