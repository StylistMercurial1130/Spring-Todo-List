package com.personal.todoapp.services.Auth;

import java.util.Optional;

import com.personal.todoapp.Models.entities.User;
import com.personal.todoapp.Repository.UsersRepository;

public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private boolean userExists(String emailId) {
        return usersRepository
        .findByEmail(emailId)
        .isPresent();
    }

    public Optional<User> createUser(User user) {
        if (userExists(user.getEmailId())) {
            return Optional.empty();
        }
        
        return Optional.of(usersRepository.save(user));
    } 

    public Optional<User> deleteUser(String emailId) {
        if (!userExists(emailId)) {
            return Optional.empty();
        }

        var user = usersRepository.findByEmail(emailId).get();
        usersRepository.delete(user); 
        
        return Optional.of(user);
    }

    public Optional<User> getUser(String emailId) {
        return usersRepository.findByEmail(emailId);
    }
}
