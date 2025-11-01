package com.ooredoo.report_builder.controller.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PasswordSetupRequest {
    @NotBlank(message = "Activation code is mandatory")
    @Pattern(regexp = "^\\d{6}$", message = "Activation code must be 6 digits")
    private String activationCode;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    public PasswordSetupRequest() {}

    public PasswordSetupRequest(String activationCode, String password) {
        this.activationCode = activationCode;
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
