package com.ooredoo.report_builder.handler;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String, String> validationErrors;
    private String fieldName;
    private Object rejectedValue;

    public ValidationException(String message) {
        super(message);
        this.validationErrors = new HashMap<>();
    }

    public ValidationException(String message, Map<String, String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors != null ? validationErrors : new HashMap<>();
    }

    public ValidationException(String fieldName, String message, Object rejectedValue) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.validationErrors = new HashMap<>();
        this.validationErrors.put(fieldName, message);
    }

    // Helper method to add validation errors
    public void addValidationError(String field, String error) {
        if (this.validationErrors == null) {
            this.validationErrors = new HashMap<>();
        }
        this.validationErrors.put(field, error);
    }

    public boolean hasErrors() {
        return validationErrors != null && !validationErrors.isEmpty();
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
