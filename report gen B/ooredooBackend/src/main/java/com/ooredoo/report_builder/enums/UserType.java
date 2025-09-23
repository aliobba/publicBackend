package com.ooredoo.report_builder.enums;

public enum UserType {

    SIMPLE_USER("SIMPLE_USER"),
    HEAD_OF_SECTOR("HEAD_OF_SECTOR"),
    HEAD_OF_ZONE("HEAD_OF_ZONE"),
    HEAD_OF_REGION("HEAD_OF_REGION"),
    HEAD_OF_POS("HEAD_OF_POS"),
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