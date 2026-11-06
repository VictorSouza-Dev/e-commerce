package org.example.ecommerce.application.mapper;

import org.example.ecommerce.domain.entities.UserProfile;
import org.example.ecommerce.shared.communication.request.userProfile.RequestRegisterUserProfileJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toEntity(RequestRegisterUserProfileJson request);


    ResponseUserProfileJson toResponse(UserProfile userProfile);
}
