package org.example.ecommerce.infraestructure.security.cryptography;

import org.example.ecommerce.domain.security.cryptography.PasswordEncripter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Argon2Cryptography implements PasswordEncripter {

    private final Argon2PasswordEncoder encoder;

    public Argon2Cryptography()
    {
        this.encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean checkPassword(String passwordHash, String password) {
        return encoder.matches(passwordHash, password);
    }
}
