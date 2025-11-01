package com.ooredoo.report_builder.controller.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ResendActivationRequest {
    @Email(message = "Email is not well formatted")
    @NotBlank(message = "Email is mandatory")
    private String email;

    public ResendActivationRequest() {}

    public ResendActivationRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
