package org.example.ecommerce.domain.services.loggedUser;

import jakarta.validation.constraints.NotNull;
import org.example.ecommerce.domain.entities.User;

import java.util.Optional;

public interface LoggedUser {
    @NotNull
    User getLoggedUser();
}
