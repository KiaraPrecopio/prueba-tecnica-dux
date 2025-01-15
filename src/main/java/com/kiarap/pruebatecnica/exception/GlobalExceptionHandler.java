package com.kiarap.pruebatecnica.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getStatus().value()); 
        response.put("error", ex.getStatus().getReasonPhrase());
        response.put("message", ex.getMessage());
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(ex.getStatus()).body(response);
    }
}
