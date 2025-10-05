package com.ooredoo.report_builder.handler;

import java.util.HashMap;
import java.util.Map;

public class FormBuilderException extends RuntimeException {

    private String errorCode;
    private Map<String, Object> additionalInfo;

    public FormBuilderException(String message) {
        super(message);
        this.errorCode = "FORM_BUILDER_ERROR";
        this.additionalInfo = new HashMap<>();
    }

    public FormBuilderException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.additionalInfo = new HashMap<>();
    }

    public FormBuilderException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "FORM_BUILDER_ERROR";
        this.additionalInfo = new HashMap<>();
    }

    public FormBuilderException(String message, String errorCode, Map<String, Object> additionalInfo) {
        super(message);
        this.errorCode = errorCode;
        this.additionalInfo = additionalInfo != null ? additionalInfo : new HashMap<>();
    }

    // Helper method to add additional info
    public void addInfo(String key, Object value) {
        if (this.additionalInfo == null) {
            this.additionalInfo = new HashMap<>();
        }
        this.additionalInfo.put(key, value);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
