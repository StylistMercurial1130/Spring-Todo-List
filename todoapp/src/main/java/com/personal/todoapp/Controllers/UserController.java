package com.personal.todoapp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.todoapp.Exceptions.ConflictException;
import com.personal.todoapp.Models.dto.UserDto;
import com.personal.todoapp.services.Auth.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<Void> createUser(@RequestBody UserDto user) {
        if (userService.getUser(user.emailId).isPresent()) {
            throw new ConflictException("cant create a new user, user exists !");
        }

        userService.createUser(user);

        return ResponseEntity.ok().build();
    }
}
