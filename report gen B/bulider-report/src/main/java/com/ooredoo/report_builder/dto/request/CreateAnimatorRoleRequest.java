package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;


public class CreateAnimatorRoleRequest {
    @NotBlank(message = "Role name is required")
    private String name;

    private String description;

    private Set<Integer> actionIds;

    public CreateAnimatorRoleRequest(@NotBlank(message = "Role name is required") String name, String description, Set<Integer> actionIds) {
        this.name = name;
        this.description = description;
        this.actionIds = actionIds;
    }

    public CreateAnimatorRoleRequest() {
    }

    public static CreateAnimatorRoleRequestBuilder builder() {
        return new CreateAnimatorRoleRequestBuilder();
    }

    public @NotBlank(message = "Role name is required") String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Integer> getActionIds() {
        return this.actionIds;
    }

    public void setName(@NotBlank(message = "Role name is required") String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActionIds(Set<Integer> actionIds) {
        this.actionIds = actionIds;
    }

    public static class CreateAnimatorRoleRequestBuilder {
        private @NotBlank(message = "Role name is required") String name;
        private String description;
        private Set<Integer> actionIds;

        CreateAnimatorRoleRequestBuilder() {
        }

        public CreateAnimatorRoleRequestBuilder name(@NotBlank(message = "Role name is required") String name) {
            this.name = name;
            return this;
        }

        public CreateAnimatorRoleRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateAnimatorRoleRequestBuilder actionIds(Set<Integer> actionIds) {
            this.actionIds = actionIds;
            return this;
        }

        public CreateAnimatorRoleRequest build() {
            return new CreateAnimatorRoleRequest(this.name, this.description, this.actionIds);
        }

        public String toString() {
            return "CreateAnimatorRoleRequest.CreateAnimatorRoleRequestBuilder(name=" + this.name + ", description=" + this.description + ", actionIds=" + this.actionIds + ")";
        }
    }
}