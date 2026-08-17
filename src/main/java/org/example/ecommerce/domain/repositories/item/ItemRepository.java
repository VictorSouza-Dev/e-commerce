package org.example.ecommerce.domain.repositories.item;

import org.example.ecommerce.domain.entities.Item;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {
    Item save(Item item);
    Optional<Item> getByPublicId(UUID id);
    void delete(Item item);
}
