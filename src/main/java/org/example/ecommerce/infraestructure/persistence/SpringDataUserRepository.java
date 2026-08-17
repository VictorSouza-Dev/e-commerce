package org.example.ecommerce.infraestructure.persistence;

import org.example.ecommerce.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdentifier(UUID uuid);
    boolean existsByEmailAndActiveTrue(String email);
    boolean existsByUserIdentifierAndActiveTrue(UUID identifier);
    Optional<User> findByEmail(String email);
}
