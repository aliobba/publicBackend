package com.ooredoo.report_builder.enums;

public enum UserType {
    REGULAR_USER("REGULAR_USER"),
    POS_USER("POS_USER");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String value) {
        for (UserType type : UserType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown user type: " + value);
    }
}