package com.cassiomolin.example.common.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Model that represents an API validation error.
 *
 * @author cassiomolin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiValidationError extends ApiError {

    private List<ValidationError> validationErrors;

    public ApiValidationError() {

    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    /**
     * Model that represents a validation error.
     *
     * @author cassiomolin
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidationError {

        private String type;

        private String name;

        private String message;

        public ValidationError() {

        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
