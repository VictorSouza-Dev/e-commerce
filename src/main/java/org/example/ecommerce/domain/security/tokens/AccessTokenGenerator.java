package org.example.ecommerce.domain.security.tokens;

import org.example.ecommerce.domain.entities.User;

public interface AccessTokenGenerator {
    String generateToken(User user);
}

