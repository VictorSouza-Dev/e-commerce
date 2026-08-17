package org.example.ecommerce.shared.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends ExceptionBaseProject{

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public List<String> getErrors() {
        return List.of(getMessage());
    }
}
