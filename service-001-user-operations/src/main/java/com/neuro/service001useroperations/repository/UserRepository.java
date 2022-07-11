package com.neuro.service001useroperations.repository;

import com.neuro.service001useroperations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findUsersByName(String name);
}
