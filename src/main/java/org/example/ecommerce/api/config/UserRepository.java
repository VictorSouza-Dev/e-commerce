package org.example.ecommerce.api.config;

import org.example.ecommerce.domain.entities.User;
import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User, Long> {
}
