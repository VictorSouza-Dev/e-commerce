package org.example.ecommerce.infraestructure.persistence.repositories;

import org.example.ecommerce.domain.entities.UserProfile;
import org.example.ecommerce.domain.repositories.userProfile.UserProfileReadOnlyRepository;
import org.example.ecommerce.domain.repositories.userProfile.UserProfileWriteOnlyRepository;
import org.example.ecommerce.infraestructure.persistence.SpringDataUserProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserProfileRepository implements UserProfileReadOnlyRepository, UserProfileWriteOnlyRepository {

    private final SpringDataUserProfileRepository repository;

    public JpaUserProfileRepository(
            SpringDataUserProfileRepository repository
    ){
        this.repository = repository;
    }

    @Override
    public UserProfile add(UserProfile userProfile) {
        return repository.save(userProfile);
    }
}
