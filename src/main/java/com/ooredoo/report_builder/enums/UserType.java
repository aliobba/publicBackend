package com.ooredoo.report_builder.enums;

public enum UserType {

    INTERNAL("INTERNAL"),
    POS("POS"),
    USER_ADMIN("USER_ADMIN");


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