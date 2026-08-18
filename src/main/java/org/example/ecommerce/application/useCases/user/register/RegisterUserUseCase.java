package org.example.ecommerce.application.useCases.user.register;

import org.example.ecommerce.application.mapper.UserMapper;
import org.example.ecommerce.domain.security.cryptography.PasswordEncripter;
import org.example.ecommerce.domain.security.tokens.AccessTokenGenerator;
import org.example.ecommerce.shared.communication.request.user.RequestRegisterUserJson;
import org.example.ecommerce.domain.repositories.user.UserRepository;
import org.example.ecommerce.shared.communication.response.ResponseTokensJson;
import org.example.ecommerce.shared.communication.response.user.ResponseRegisteredUserJson;
import org.example.ecommerce.shared.exception.ErrorOnValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterUserUseCase {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final AccessTokenGenerator accessTokenGenerator;
    private final PasswordEncripter  passwordEncripter;

    public RegisterUserUseCase(
            UserRepository repository,
            UserMapper mapper,
            AccessTokenGenerator accessTokenGenerator,
            PasswordEncripter passwordEncripter
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.accessTokenGenerator = accessTokenGenerator;
        this.passwordEncripter = passwordEncripter;
    }

    public ResponseRegisteredUserJson execute(RequestRegisterUserJson request){

        if(repository.existsActiveUserByEmail(request.getEmail())){
            throw new ErrorOnValidationException(List.of("Email já cadastrado"));
        }

        var user = mapper.toUser(request);
        user.setPassword(passwordEncripter.encrypt(request.getPassword()));

        repository.save(user);

        return ResponseRegisteredUserJson.builder()
                .email(request.getEmail())
                .tokens(
                        ResponseTokensJson.builder()
                                .accessToken(accessTokenGenerator.generateToken(user))
                                .build()
                )
                .build();
    }
}
