package org.example.ecommerce.application.useCases.userProfile.register;

import org.example.ecommerce.application.mapper.UserProfileMapper;
import org.example.ecommerce.domain.entities.UserProfile;
import org.example.ecommerce.domain.repositories.userProfile.UserProfileWriteOnlyRepository;
import org.example.ecommerce.domain.services.loggedUser.LoggedUser;
import org.example.ecommerce.shared.communication.request.userProfile.RequestRegisterUserProfileJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserProfileUseCaseImpl implements RegisterUserProfileUseCase {

    private final UserProfileWriteOnlyRepository repository;
    private final UserProfileMapper mapper;
    private final LoggedUser loggedUser;

    public RegisterUserProfileUseCaseImpl(
            UserProfileWriteOnlyRepository repository,
            UserProfileMapper mapper,
            LoggedUser loggedUser
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public ResponseUserProfileJson execute(RequestRegisterUserProfileJson request) {
        var userLogged =  loggedUser.getLoggedUser();

        var userProfile = mapper.toEntity(request);
        userProfile.setPhoneNumber("+" + userProfile.getPhoneNumber());
        userProfile.setUser(userLogged);
        userProfile.setPhoneNumber("+55" + request.setPhoneNumber(););

        return mapper.toResponse(userProfile);

    }
}
