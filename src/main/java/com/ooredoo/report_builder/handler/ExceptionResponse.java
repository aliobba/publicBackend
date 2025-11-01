package com.ooredoo.report_builder.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;

    public ExceptionResponse(Integer businessErrorCode, String businessErrorDescription, String error, Set<String> validationErrors, Map<String, String> errors) {
        this.businessErrorCode = businessErrorCode;
        this.businessErrorDescription = businessErrorDescription;
        this.error = error;
        this.validationErrors = validationErrors;
        this.errors = errors;
    }

    public ExceptionResponse() {
    }

    public static ExceptionResponseBuilder builder() {
        return new ExceptionResponseBuilder();
    }

    public Integer getBusinessErrorCode() {
        return this.businessErrorCode;
    }

    public void setBusinessErrorCode(Integer businessErrorCode) {
        this.businessErrorCode = businessErrorCode;
    }

    public String getBusinessErrorDescription() {
        return this.businessErrorDescription;
    }

    public void setBusinessErrorDescription(String businessErrorDescription) {
        this.businessErrorDescription = businessErrorDescription;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Set<String> getValidationErrors() {
        return this.validationErrors;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getErrors() {
        return this.errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public static class ExceptionResponseBuilder {
        private Integer businessErrorCode;
        private String businessErrorDescription;
        private String error;
        private Set<String> validationErrors;
        private Map<String, String> errors;

        ExceptionResponseBuilder() {
        }

        public ExceptionResponseBuilder businessErrorCode(Integer businessErrorCode) {
            this.businessErrorCode = businessErrorCode;
            return this;
        }

        public ExceptionResponseBuilder businessErrorDescription(String businessErrorDescription) {
            this.businessErrorDescription = businessErrorDescription;
            return this;
        }

        public ExceptionResponseBuilder error(String error) {
            this.error = error;
            return this;
        }

        public ExceptionResponseBuilder validationErrors(Set<String> validationErrors) {
            this.validationErrors = validationErrors;
            return this;
        }

        public ExceptionResponseBuilder errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public ExceptionResponse build() {
            return new ExceptionResponse(this.businessErrorCode, this.businessErrorDescription, this.error, this.validationErrors, this.errors);
        }

        public String toString() {
            return "ExceptionResponse.ExceptionResponseBuilder(businessErrorCode=" + this.businessErrorCode + ", businessErrorDescription=" + this.businessErrorDescription + ", error=" + this.error + ", validationErrors=" + this.validationErrors + ", errors=" + this.errors + ")";
        }
    }
}
