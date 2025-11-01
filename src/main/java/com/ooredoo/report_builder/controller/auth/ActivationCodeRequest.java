package com.ooredoo.report_builder.controller.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ActivationCodeRequest {
    @NotBlank(message = "Activation code is mandatory")
    @Pattern(regexp = "^\\d{6}$", message = "Activation code must be 6 digits")
    private String code;

    public ActivationCodeRequest() {}

    public ActivationCodeRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
