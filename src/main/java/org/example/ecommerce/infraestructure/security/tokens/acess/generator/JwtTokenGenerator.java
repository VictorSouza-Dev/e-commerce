package org.example.ecommerce.infraestructure.security.tokens.acess.generator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.ecommerce.domain.entities.User;
import org.example.ecommerce.domain.security.tokens.AccessTokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenGenerator implements AccessTokenGenerator {

    private final long JWT_TOKEN_EXPIRATION;
    private final SecretKey KEY;

    public JwtTokenGenerator(
            @Value("${security.jwt.secret}") String JWT_TOKEN_SECRET,
            @Value("${security.jwt.expiration}") long JWT_TOKEN_EXPIRATION
    ) {
        this.JWT_TOKEN_EXPIRATION = JWT_TOKEN_EXPIRATION;
        this.KEY = Keys.hmacShaKeyFor(JWT_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user){

        Map<String, String> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("uuid", user.getUserIdentifier().toString());

        return Jwts.builder()
                .subject(user.getEmail())
                .claims(claims)
                .id(user.getUserIdentifier().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION))
                .signWith(this.KEY)
                .compact();
    }
}
