package org.example.ecommerce.shared.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class ExceptionBaseProject extends RuntimeException {

    public ExceptionBaseProject(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();

    public abstract List<String> getErrors();
}
