package org.example.ecommerce.api.token;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.ecommerce.domain.security.tokens.TokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@Component
@RequestScope
public class HttpContextTokenValue implements TokenProvider {

    private final HttpServletRequest request;

    public HttpContextTokenValue(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String value() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String tokenFromCookie = Arrays.stream(cookies)
                    .filter(cookie -> "access_Token".equalsIgnoreCase(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);

            if (tokenFromCookie != null && !tokenFromCookie.isBlank()) {
                return tokenFromCookie;
            }
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}