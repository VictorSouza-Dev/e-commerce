package org.example.ecommerce.domain.security.tokens;

import java.util.UUID;

public interface AccessTokenValidator {
    UUID validateAndGetUserIdentifier(String token);
}
