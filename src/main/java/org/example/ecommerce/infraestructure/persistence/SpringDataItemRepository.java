package org.example.ecommerce.infraestructure.persistence;

import org.example.ecommerce.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> getByPublicId(UUID id);
}
