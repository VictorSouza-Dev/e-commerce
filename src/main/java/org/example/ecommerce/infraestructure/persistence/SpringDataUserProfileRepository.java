package org.example.ecommerce.infraestructure.persistence;

import org.example.ecommerce.domain.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserProfileRepository extends JpaRepository<UserProfile, Long> {
}
