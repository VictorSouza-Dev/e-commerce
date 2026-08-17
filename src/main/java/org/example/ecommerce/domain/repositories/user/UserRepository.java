package org.example.ecommerce.domain.repositories.user;

import org.example.ecommerce.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository{
    Optional<User> findByUUID(UUID uuid);
    void save(User user);
    boolean existsActiveUserByEmail(String email);
    boolean existsActiveUserByIdentifier(UUID identifier);
    Optional<User> findByEmail(String email);
}
