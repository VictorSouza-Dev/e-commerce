package org.example.ecommerce.application.useCases.item.getAll;

import org.example.ecommerce.application.mapper.ItemMapper;
import org.example.ecommerce.domain.repositories.item.ItemRepository;
import org.example.ecommerce.shared.communication.response.item.ResponseItemsJson;
import org.example.ecommerce.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetAllItemUseCase {

    private final ItemRepository repository;
    private final ItemMapper mapper;

    public GetAllItemUseCase(
            ItemRepository repository,
            ItemMapper mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponseItemsJson execute(){

        var items = repository.getAll();

        if (items.isEmpty()){
            throw new NotFoundException("Items not found");
        }

        return mapper.toListResponse(items);
    }
}
