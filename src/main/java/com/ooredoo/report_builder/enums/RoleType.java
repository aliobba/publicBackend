package com.ooredoo.report_builder.enums;

public enum RoleType {
    MAIN_ADMIN("MAIN_ADMIN"),
    ENTERPRISE_ADMIN("ENTERPRISE_ADMIN"),
    SIMPLE_USER("SIMPLE_USER"),
    HEAD_OF_SECTOR("HEAD_OF_SECTOR"),
    HEAD_OF_ZONE("HEAD_OF_ZONE"),
    HEAD_OF_REGION("HEAD_OF_REGION"),
    HEAD_OF_POS("HEAD_OF_POS"),
    COMMERCIAL_POS("COMMERCIAL_POS");

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
