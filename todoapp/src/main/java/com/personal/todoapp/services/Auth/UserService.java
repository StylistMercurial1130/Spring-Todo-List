package com.personal.todoapp.services.Auth;

import java.util.Optional;

import com.personal.todoapp.Models.dto.UserDto;
import com.personal.todoapp.Models.entities.User;
import com.personal.todoapp.Models.mapper.UserMapper;
import com.personal.todoapp.Repository.UsersRepository;

public class UserService {
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public UserService(UsersRepository usersRepository,UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    private boolean userExists(String emailId) {
        return usersRepository
        .findByEmailId(emailId)
        .isPresent();
    }

    public Optional<User> createUser(UserDto user) {
        if (userExists(user.emailId)) {
            return Optional.empty();
        }
        
        return Optional.of(usersRepository.save(userMapper.fromDto(user)));
    } 

    public Optional<User> deleteUser(String emailId) {
        if (!userExists(emailId)) {
            return Optional.empty();
        }

        var user = usersRepository.findByEmailId(emailId).get();
        usersRepository.delete(user); 
        
        return Optional.of(user);
    }

    public Optional<User> getUser(String emailId) {
        return usersRepository.findByEmailId(emailId);
    }
}
