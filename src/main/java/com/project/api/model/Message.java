package com.project.api.model;

import org.springframework.stereotype.Component;

@Component
public class Message {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
