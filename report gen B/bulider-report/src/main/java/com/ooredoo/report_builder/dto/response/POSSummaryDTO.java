package com.ooredoo.report_builder.dto.response;


public class POSSummaryDTO {
    private Integer id;
    private String name;
    private String location;

    public POSSummaryDTO(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public POSSummaryDTO() {
    }

    public static POSSummaryDTOBuilder builder() {
        return new POSSummaryDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class POSSummaryDTOBuilder {
        private Integer id;
        private String name;
        private String location;

        POSSummaryDTOBuilder() {
        }

        public POSSummaryDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public POSSummaryDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public POSSummaryDTOBuilder location(String location) {
            this.location = location;
            return this;
        }

        public POSSummaryDTO build() {
            return new POSSummaryDTO(this.id, this.name, this.location);
        }

        public String toString() {
            return "POSSummaryDTO.POSSummaryDTOBuilder(id=" + this.id + ", name=" + this.name + ", location=" + this.location + ")";
        }
    }
}
