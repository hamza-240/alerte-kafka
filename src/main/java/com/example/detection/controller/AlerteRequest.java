package com.example.detection.controller;

// AlerteRequest.java
public class AlerteRequest {
    private String userId;
    private String message;
    // Getters + Setters


    public AlerteRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}