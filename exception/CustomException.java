package com.trimind.restdemo1.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<Object>handleUserNotFoundExceptions(UserNotFound ex, WebRequest rs){

        ExceptionStructure es=new ExceptionStructure(ex.getMessage(),rs.getDescription(false));
        return new ResponseEntity(es, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<Object>handleInvalidFormatExceptions( InvalidFormatException ex, WebRequest rs){

        ExceptionStructure es=new ExceptionStructure(ex.getMessage(),rs.getDescription(false));
        return new ResponseEntity(es, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public final ResponseEntity<Object>handleDuplicateDataExceptions( DuplicateDataException ex, WebRequest rs){

        ExceptionStructure es=new ExceptionStructure(ex.getMessage(),rs.getDescription(true));
        return new ResponseEntity(es, HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionStructure es=new ExceptionStructure(ex.getBindingResult().getFieldError().getDefaultMessage(),request.getDescription(false));
        return  new ResponseEntity<Object>(es,HttpStatus.BAD_REQUEST);
    }
}
