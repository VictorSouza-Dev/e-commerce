package org.example.ecommerce.domain.security.tokens;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    String value();
}
