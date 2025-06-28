package com.ooredoo.report_builder.dto.response;

public class AnimatorRoleSummaryDTO {
    private Integer id;
    private String name;

    public AnimatorRoleSummaryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnimatorRoleSummaryDTO() {
    }

    public static AnimatorRoleSummaryDTOBuilder builder() {
        return new AnimatorRoleSummaryDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class AnimatorRoleSummaryDTOBuilder {
        private Integer id;
        private String name;

        AnimatorRoleSummaryDTOBuilder() {
        }

        public AnimatorRoleSummaryDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AnimatorRoleSummaryDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AnimatorRoleSummaryDTO build() {
            return new AnimatorRoleSummaryDTO(this.id, this.name);
        }

        public String toString() {
            return "AnimatorRoleSummaryDTO.AnimatorRoleSummaryDTOBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
