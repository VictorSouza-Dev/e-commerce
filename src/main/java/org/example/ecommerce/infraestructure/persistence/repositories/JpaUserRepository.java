package org.example.ecommerce.infraestructure.persistence.repositories;

import org.example.ecommerce.domain.entities.User;
import org.example.ecommerce.domain.repositories.user.UserRepository;
import org.example.ecommerce.infraestructure.persistence.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;

    public JpaUserRepository(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByUUID(UUID uuid) {
        return this.repository.findByUserIdentifier(uuid);
    }

    @Override
    public void save(User user) {
        this.repository.save(user);
    }

    @Override
    public boolean existsActiveUserByEmail(String email) {
        return repository.existsByEmailAndActiveTrue(email);
    }

    @Override
    public boolean existsActiveUserByIdentifier(UUID identifier) {
        return repository.existsByUserIdentifierAndActiveTrue(identifier);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
