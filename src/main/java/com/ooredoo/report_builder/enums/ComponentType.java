package com.ooredoo.report_builder.enums;

public enum ComponentType {
    TEXT("text", "Text Input"),
    EMAIL("email", "Email Input"),
    NUMBER("number", "Number Input"),
    TEXTAREA("textarea", "Text Area"),
    DROPDOWN("dropdown", "Dropdown Select"),
    RADIO("radio", "Radio Button Group"),
    CHECKBOX("checkbox", "Checkbox Group"),
    DATE("date", "Date Picker"),
    FILE_UPLOAD("file_upload", "File Upload");

    private final String value;
    private final String displayName;

    ComponentType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static ComponentType fromValue(String value) {
        for (ComponentType type : ComponentType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown component type: " + value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }
}
