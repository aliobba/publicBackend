package com.ooredoo.report_builder.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResultDTO {
    private Boolean isValid;
    private String message;
    private Map<String, String> fieldErrors;
    private List<String> warnings;

    // Constructors
    public ValidationResultDTO() {
    }

    public ValidationResultDTO(Boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
        this.fieldErrors = new HashMap<>();
        this.warnings = new ArrayList<>();
    }

    public ValidationResultDTO(Boolean isValid, String message, Map<String, String> fieldErrors, List<String> warnings) {
        this.isValid = isValid;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.warnings = warnings;
    }

    public static ValidationResultDTO success() {
        return new ValidationResultDTO(true, "Validation passed");
    }

    public static ValidationResultDTO failure(String message) {
        return new ValidationResultDTO(false, message);
    }

    public void addFieldError(String field, String error) {
        if (fieldErrors == null) {
            fieldErrors = new HashMap<>();
        }
        fieldErrors.put(field, error);
        this.isValid = false;
    }

    public void addWarning(String warning) {
        if (warnings == null) {
            warnings = new ArrayList<>();
        }
        warnings.add(warning);
    }

    public Boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFieldErrors() {
        return this.fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<String> getWarnings() {
        return this.warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
