package org.example.ecommerce.infraestructure.security.tokens.acess.validator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.ecommerce.domain.security.tokens.AccessTokenValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class JwtTokenValidator implements AccessTokenValidator {

    private final String secretKey;

    public JwtTokenValidator(
            @Value("${security.jwt.secret}") String secretKey
    ){
        this.secretKey = secretKey;
    }

    @Override
    public UUID validateAndGetUserIdentifier(String token) {
        var claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getPayload();

        return UUID.fromString(claims.getId());
    }
}
