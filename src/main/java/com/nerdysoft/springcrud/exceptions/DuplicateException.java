package com.nerdysoft.springcrud.exceptions;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
}
