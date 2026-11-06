package org.example.ecommerce.infraestructure.services.loggedUser;

import jakarta.validation.constraints.NotNull;
import org.example.ecommerce.domain.entities.User;
import org.example.ecommerce.domain.security.tokens.AccessTokenValidator;
import org.example.ecommerce.domain.security.tokens.TokenProvider;
import org.example.ecommerce.domain.services.loggedUser.LoggedUser;
import org.example.ecommerce.infraestructure.persistence.repositories.JpaUserRepository;
import org.example.ecommerce.shared.exception.InvalidLoginException;
import org.example.ecommerce.shared.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HttpLoggedUser implements LoggedUser {

    private final JpaUserRepository repository;
    private final AccessTokenValidator validator;
    private final TokenProvider tokenProvider;

    public HttpLoggedUser(
            JpaUserRepository repository,
            AccessTokenValidator validator,
            TokenProvider tokenProvider
    ){
        this.repository = repository;
        this.validator = validator;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @NotNull
    public User getLoggedUser() {
       var token = tokenProvider.value();

       var userIdentifier = validator.validateAndGetUserIdentifier(token);

       return repository.findByUUID(userIdentifier)
               .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
