package com.ooredoo.report_builder.enums;

public enum RoleType {
    MAIN_ADMIN("MAIN_ADMIN"),
    ENTREPRISE_ADMIN("ENTREPRISE_ADMIN"),
    USER("USER");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleType fromValue(String value) {
        for (RoleType type : RoleType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown role type: " + value);
    }
}
