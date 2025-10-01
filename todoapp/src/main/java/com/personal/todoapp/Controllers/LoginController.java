package com.personal.todoapp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.todoapp.Exceptions.UnAuthorizedUserException;
import com.personal.todoapp.Models.dto.ApiToken;
import com.personal.todoapp.Models.dto.UserDto;
import com.personal.todoapp.services.Auth.AuthService;
import com.personal.todoapp.services.Auth.UserService;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final UserService userService; 
    private final AuthService authService;
    
    public LoginController(UserService userService,AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    } 
    
    @GetMapping("/login")
    public ResponseEntity<ApiToken> login(@RequestBody UserDto userCredentilas) {
        var user = userService.getUser(userCredentilas.emailId);

        if (user.isEmpty() || !user.get().getPassword().equals(userCredentilas.password)) {
            throw new UnAuthorizedUserException("unauthorized user");
        } 

        var token = new ApiToken(authService.generateToken(userCredentilas.emailId).get());

        return ResponseEntity.ok(token);
    }
}
