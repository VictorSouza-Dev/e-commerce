package org.example.ecommerce.application.mapper;

import org.example.ecommerce.domain.entities.Item;
import org.example.ecommerce.shared.communication.response.item.ResponseItemSummaryJson;
import org.example.ecommerce.shared.communication.response.item.ResponseItemsJson;
import org.example.ecommerce.shared.communication.response.item.ResponseRegisteredItemJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "category.name", target = "category")
    ResponseRegisteredItemJson toRegisteredResponse(Item item);

    ResponseItemSummaryJson toSummaryResponse(Item item);

    List<ResponseItemSummaryJson> toSummaryResponseList(List<Item> items);

    default ResponseItemsJson toListResponse(List<Item> items) {
        return ResponseItemsJson.builder()
                .items(toSummaryResponseList(items))
                .build();
    }
}