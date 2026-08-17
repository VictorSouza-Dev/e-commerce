package org.example.ecommerce.infraestructure.persistence.repositories;

import org.example.ecommerce.domain.entities.Category;
import org.example.ecommerce.domain.repositories.category.CategoryRepository;
import org.example.ecommerce.infraestructure.persistence.SpringDataCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringDataCategoryRepository repository;

    public JpaCategoryRepository(SpringDataCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Category> findByUuid(UUID uuid) {
        return findByUuid(uuid);
    }
}
