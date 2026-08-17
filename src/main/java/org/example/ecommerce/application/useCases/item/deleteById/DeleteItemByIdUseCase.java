package org.example.ecommerce.application.useCases.item.deleteById;

import com.cloudinary.Cloudinary;
import org.example.ecommerce.domain.repositories.item.ItemRepository;
import org.example.ecommerce.domain.services.Storage.ImageStoragePort;
import org.example.ecommerce.infraestructure.config.CloudinaryConfig;
import org.example.ecommerce.shared.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteItemByIdUseCase {

    private final ItemRepository repository;
    private final ImageStoragePort storage;

    public DeleteItemByIdUseCase(
            ItemRepository itemRepository,
            ImageStoragePort storage
    ){
        this.repository = itemRepository;
        this.storage = storage;
    }

    public void execute(UUID itemId){
        var item = repository.getByPublicId(itemId)
                .orElseThrow(() -> new NotFoundException("item not found"));

        storage.delete(itemId);

        repository.delete(item);


    }
}
