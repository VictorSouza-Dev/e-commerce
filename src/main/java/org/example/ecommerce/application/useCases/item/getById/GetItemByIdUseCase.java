package org.example.ecommerce.application.useCases.item.getById;

import org.example.ecommerce.application.services.mapper.AutoMapping;
import org.example.ecommerce.domain.repositories.item.ItemRepository;
import org.example.ecommerce.shared.communication.response.item.ResponseRegisteredItemJson;
import org.example.ecommerce.shared.exception.NotFoundException;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetItemByIdUseCase {

    private final AutoMapping mapper;
    private final ItemRepository repository;

    public GetItemByIdUseCase(
            AutoMapping mapper,
            ItemRepository repository
    ){
        this.mapper = mapper;
        this.repository = repository;
    }

    public ResponseRegisteredItemJson execute(UUID id){
        var item = repository.getByPublicId(id).orElseThrow(() -> new NotFoundException("Item not found"));

        return mapper.itemToResponse(item);
    }
}
