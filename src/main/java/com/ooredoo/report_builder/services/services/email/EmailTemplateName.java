package com.ooredoo.report_builder.services.services.email;


import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account"),
    OTP_VERIFICATION("otp_verification"),
    PASSWORD_SETUP_INVITATION("password_setup_invitation");

    private final String name;

    private EmailTemplateName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
