package com.trimind.restdemo1.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionStructure {
    private String message;

    private String details;

    public ExceptionStructure(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public ExceptionStructure() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ExceptionStructure{" +
                "message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
