package com.nerdysoft.springcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LoginFailedException.class)
    protected ResponseEntity<Object> handleLoginFailedException(LoginFailedException e) {
        String bodyOfResponse = e.getMessage();
        HttpStatus responseStatus = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(bodyOfResponse, responseStatus);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleNotFoundException(IllegalArgumentException e) {
        String bodyOfResponse = e.getMessage();
        HttpStatus responseStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(bodyOfResponse, responseStatus);
    }

    @ExceptionHandler(value = DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateException(DuplicateException e) {
        String bodyOfResponse = e.getMessage();
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(bodyOfResponse, responseStatus);
    }


}
