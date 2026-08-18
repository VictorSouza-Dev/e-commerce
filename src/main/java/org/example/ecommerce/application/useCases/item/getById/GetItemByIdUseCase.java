package org.example.ecommerce.application.useCases.item.getById;

import org.example.ecommerce.application.mapper.ItemMapper;
import org.example.ecommerce.domain.repositories.item.ItemRepository;
import org.example.ecommerce.shared.communication.response.item.ResponseItemSummaryJson;
import org.example.ecommerce.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetItemByIdUseCase {

    private final ItemMapper mapper;
    private final ItemRepository repository;

    public GetItemByIdUseCase(
            ItemMapper mapper,
            ItemRepository repository
    ){
        this.mapper = mapper;
        this.repository = repository;
    }

    public ResponseItemSummaryJson execute(UUID id){

        var item = repository.getByPublicId(id).orElseThrow(() -> new NotFoundException("Item not found"));

        return mapper.toSummaryResponse(item);
    }
}
