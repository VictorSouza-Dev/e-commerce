package org.example.ecommerce.application.useCases.user.profile;

import org.example.ecommerce.application.mapper.UserMapper;
import org.example.ecommerce.domain.entities.User;
import org.example.ecommerce.domain.services.loggedUser.LoggedUser;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.example.ecommerce.shared.exception.InvalidLoginException;
import org.springframework.stereotype.Service;

@Service
public class GetUserProfileUseCase {

    private LoggedUser loggedUser;
    private final UserMapper mapper;

    public GetUserProfileUseCase(
            LoggedUser loggedUser,
            UserMapper mapper
    ) {
        this.loggedUser = loggedUser;
        this.mapper = mapper;
    }

    public ResponseUserProfileJson execute(){

        User user = loggedUser.getLoggedUser().orElseThrow(() -> new InvalidLoginException());

        return mapper.toResponse(user);
    }
}
