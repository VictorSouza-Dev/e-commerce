package org.example.ecommerce.api.filters;

import org.example.ecommerce.shared.exception.ExceptionBaseProject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(ExceptionBaseProject ex){

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getMessage());
    }
}
