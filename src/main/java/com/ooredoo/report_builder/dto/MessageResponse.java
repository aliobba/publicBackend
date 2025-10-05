package com.ooredoo.report_builder.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class MessageResponse {
    private String message;
    private LocalDateTime timestamp;
    private String status; // "success", "error", "warning", "info"
    private Map<String, Object> metadata;

    // Constructors
    public MessageResponse() {
        this.timestamp = LocalDateTime.now();
        this.status = "success";
    }

    public MessageResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = "success";
    }

    public MessageResponse(String message, String status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public MessageResponse(String message, LocalDateTime timestamp, String status, Map<String, Object> metadata) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
        this.metadata = metadata;
    }

    // Static factory methods
    public static MessageResponse success(String message) {
        return new MessageResponse(message, "success");
    }

    public static MessageResponse error(String message) {
        return new MessageResponse(message, "error");
    }

    public static MessageResponse warning(String message) {
        return new MessageResponse(message, "warning");
    }

    public static MessageResponse info(String message) {
        return new MessageResponse(message, "info");
    }

    // Helper method to add metadata
    public void addMetadata(String key, Object value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
