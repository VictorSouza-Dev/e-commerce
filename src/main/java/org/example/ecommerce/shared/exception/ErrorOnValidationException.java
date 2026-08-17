package org.example.ecommerce.shared.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorOnValidationException extends ExceptionBaseProject {

    private List<String> errors;

    public ErrorOnValidationException(List<String> errors) {
        super("");

        this.errors = errors;
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }
}
