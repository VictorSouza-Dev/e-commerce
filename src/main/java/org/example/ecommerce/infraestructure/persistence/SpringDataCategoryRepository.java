package org.example.ecommerce.infraestructure.persistence;

import org.example.ecommerce.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataCategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
