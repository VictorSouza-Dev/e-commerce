package org.example.ecommerce.application.services.mapper;

import org.example.ecommerce.domain.entities.Item;
import org.example.ecommerce.domain.entities.User;
import org.example.ecommerce.shared.communication.request.user.RequestRegisterUserJson;
import org.example.ecommerce.shared.communication.response.item.ResponseRegisteredItemJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutoMapping {
    User requestRegisterUserToUser(RequestRegisterUserJson request);
    ResponseUserProfileJson userToResponse(User user);
    @Mapping(source = "category.name", target = "category")
    ResponseRegisteredItemJson itemToResponse(Item item);
}
