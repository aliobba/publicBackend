package com.ooredoo.report_builder.services.services.email;


import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    private EmailTemplateName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
