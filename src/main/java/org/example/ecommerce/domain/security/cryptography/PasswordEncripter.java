package org.example.ecommerce.domain.security.cryptography;

public interface PasswordEncripter {

    String encrypt(String password);
    boolean checkPassword(String password, String passwordHash);

}
