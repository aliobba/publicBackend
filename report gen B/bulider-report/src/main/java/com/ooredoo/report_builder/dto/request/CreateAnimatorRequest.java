package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


public class CreateAnimatorRequest {
    @NotBlank(message = "PIN is required")
    private String pin;

    private String description;

    @NotNull(message = "Role ID is required")
    private Integer roleId;

    private Integer posId;

    private Set<Integer> userIds;

    public CreateAnimatorRequest(@NotBlank(message = "PIN is required") String pin, String description, @NotNull(message = "Role ID is required") Integer roleId, Integer posId, Set<Integer> userIds) {
        this.pin = pin;
        this.description = description;
        this.roleId = roleId;
        this.posId = posId;
        this.userIds = userIds;
    }

    public CreateAnimatorRequest() {
    }

    public static CreateAnimatorRequestBuilder builder() {
        return new CreateAnimatorRequestBuilder();
    }

    public @NotBlank(message = "PIN is required") String getPin() {
        return this.pin;
    }

    public String getDescription() {
        return this.description;
    }

    public @NotNull(message = "Role ID is required") Integer getRoleId() {
        return this.roleId;
    }

    public Integer getPosId() {
        return this.posId;
    }

    public Set<Integer> getUserIds() {
        return this.userIds;
    }

    public void setPin(@NotBlank(message = "PIN is required") String pin) {
        this.pin = pin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoleId(@NotNull(message = "Role ID is required") Integer roleId) {
        this.roleId = roleId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public static class CreateAnimatorRequestBuilder {
        private @NotBlank(message = "PIN is required") String pin;
        private String description;
        private @NotNull(message = "Role ID is required") Integer roleId;
        private Integer posId;
        private Set<Integer> userIds;

        CreateAnimatorRequestBuilder() {
        }

        public CreateAnimatorRequestBuilder pin(@NotBlank(message = "PIN is required") String pin) {
            this.pin = pin;
            return this;
        }

        public CreateAnimatorRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateAnimatorRequestBuilder roleId(@NotNull(message = "Role ID is required") Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public CreateAnimatorRequestBuilder posId(Integer posId) {
            this.posId = posId;
            return this;
        }

        public CreateAnimatorRequestBuilder userIds(Set<Integer> userIds) {
            this.userIds = userIds;
            return this;
        }

        public CreateAnimatorRequest build() {
            return new CreateAnimatorRequest(this.pin, this.description, this.roleId, this.posId, this.userIds);
        }

        public String toString() {
            return "CreateAnimatorRequest.CreateAnimatorRequestBuilder(pin=" + this.pin + ", description=" + this.description + ", roleId=" + this.roleId + ", posId=" + this.posId + ", userIds=" + this.userIds + ")";
        }
    }
}