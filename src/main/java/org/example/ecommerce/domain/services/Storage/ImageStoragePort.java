package org.example.ecommerce.domain.services.Storage;

import org.example.ecommerce.domain.entities.User;

import java.util.UUID;

public interface ImageStoragePort {
    String upload(byte[] image, UUID itemId);
    void delete(UUID id);
}
