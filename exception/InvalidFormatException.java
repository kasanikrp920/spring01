package com.trimind.restdemo1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String message) {
        super(message);

    }
}
