package org.example.ecommerce.shared.communication.response;

import lombok.Getter;

import java.util.List;

public class ResponseErrorJson {
    @Getter
    private final List<String> errors;

    @Getter
    private boolean tokenExpired;

    public ResponseErrorJson(List<String> errors) {
        this.errors = errors;
    }

    public ResponseErrorJson(String error) {
        this.errors = List.of(error);
    }
}
