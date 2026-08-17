package org.example.ecommerce.domain.repositories.category;

import org.example.ecommerce.domain.entities.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findByName(String name);
    Optional<Category> findByUuid(UUID uuid);
}
