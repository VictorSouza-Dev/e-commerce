package org.example.ecommerce.domain.services.loggedUser;

import org.example.ecommerce.domain.entities.User;

import java.util.Optional;

public interface LoggedUser {
    Optional<User> getLoggedUser();
}
