package com.personal.todoapp.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.todoapp.Models.entities.User;

public interface UsersRepository extends JpaRepository<User,UUID> {
    Optional<User> findByEmail(String email);
}
