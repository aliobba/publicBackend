package com.ooredoo.report_builder.controller.department;


public class DepartmentCreationRequest {

    private String name;
    private String description;
    private Integer adminId;

    DepartmentCreationRequest(String name, String description, Integer adminId) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
    }

    public static DepartmentCreationRequestBuilder builder() {
        return new DepartmentCreationRequestBuilder();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public static class DepartmentCreationRequestBuilder {
        private String name;
        private String description;
        private Integer adminId;

        DepartmentCreationRequestBuilder() {
        }

        public DepartmentCreationRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentCreationRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DepartmentCreationRequestBuilder adminId(Integer adminId) {
            this.adminId = adminId;
            return this;
        }

        public DepartmentCreationRequest build() {
            return new DepartmentCreationRequest(this.name, this.description, this.adminId);
        }

        public String toString() {
            return "DepartmentCreationRequest.DepartmentCreationRequestBuilder(name=" + this.name + ", description=" + this.description + ", adminId=" + this.adminId + ")";
        }
    }
}
