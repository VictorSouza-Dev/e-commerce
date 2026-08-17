package org.example.ecommerce.application.dto;

public record ImageValidationResult(
        boolean isValidImage,
        String extension
){}
