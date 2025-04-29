package com.example.bookcatalog.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> errors;

    public ValidationErrorDetails(LocalDateTime timestamp, String message, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}