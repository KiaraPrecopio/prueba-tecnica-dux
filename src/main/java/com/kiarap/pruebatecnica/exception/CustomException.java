package com.kiarap.pruebatecnica.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final HttpStatus status;

    public CustomException(HttpStatus status, String message, Object... args) {
        super(String.format(message, args));
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
