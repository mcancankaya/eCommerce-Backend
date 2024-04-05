package com.mcancankaya.ecommerce.repositories;

import com.mcancankaya.ecommerce.entities.User;
import com.mcancankaya.ecommerce.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
