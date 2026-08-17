package org.example.ecommerce.shared.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidLoginException extends  ExceptionBaseProject{

    public InvalidLoginException() {
        super("Invalid login or password");
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public List<String> getErrors() {
        return List.of(getMessage());
    }
}
