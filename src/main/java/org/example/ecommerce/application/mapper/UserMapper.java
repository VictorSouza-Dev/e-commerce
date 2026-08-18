package org.example.ecommerce.application.mapper;

import org.example.ecommerce.domain.entities.User;
import org.example.ecommerce.shared.communication.request.user.RequestRegisterUserJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RequestRegisterUserJson request);

    ResponseUserProfileJson toResponse(User user);
}
