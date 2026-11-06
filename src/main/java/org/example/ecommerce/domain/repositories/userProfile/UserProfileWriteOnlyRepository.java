package org.example.ecommerce.domain.repositories.userProfile;

import org.example.ecommerce.domain.entities.UserProfile;

public interface UserProfileWriteOnlyRepository {
    UserProfile add(UserProfile userProfile);
}
