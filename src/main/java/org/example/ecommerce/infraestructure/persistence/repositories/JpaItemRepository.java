package org.example.ecommerce.infraestructure.persistence.repositories;

import org.example.ecommerce.domain.entities.Item;
import org.example.ecommerce.domain.repositories.item.ItemRepository;
import org.example.ecommerce.infraestructure.persistence.SpringDataItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaItemRepository implements ItemRepository {

    private SpringDataItemRepository repository;

    public JpaItemRepository(SpringDataItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public Optional<Item> getByPublicId(UUID id) {
        return repository.getByPublicId(id);
    }

    @Override
    public void delete(Item item) {
        repository.delete(item);
    }

    @Override
    public List<Item> getAll() {
        return repository.findAll();
    }
}
