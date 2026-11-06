package org.example.ecommerce.application.useCases.userProfile.register;

import org.example.ecommerce.domain.entities.UserProfile;
import org.example.ecommerce.shared.communication.request.userProfile.RequestRegisterUserProfileJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;

public interface RegisterUserProfileUseCase {
    ResponseUserProfileJson execute(RequestRegisterUserProfileJson userProfile);
}
