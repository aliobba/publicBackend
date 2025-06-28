package com.ooredoo.report_builder.dto.response;


public class SectorSummaryDTO {
    private Integer id;
    private String name;

    public SectorSummaryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SectorSummaryDTO() {
    }

    public static SectorSummaryDTOBuilder builder() {
        return new SectorSummaryDTOBuilder();
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

    public static class SectorSummaryDTOBuilder {
        private Integer id;
        private String name;

        SectorSummaryDTOBuilder() {
        }

        public SectorSummaryDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SectorSummaryDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SectorSummaryDTO build() {
            return new SectorSummaryDTO(this.id, this.name);
        }

        public String toString() {
            return "SectorSummaryDTO.SectorSummaryDTOBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
