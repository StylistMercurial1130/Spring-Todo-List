package com.personal.todoapp.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.todoapp.Models.dto.ApiToken;
import com.personal.todoapp.Models.dto.UserCredentilas;

@RestController
@RequestMapping("/auth")
public class LoginController {
    
    @GetMapping("/login")
    public ApiToken login(@RequestBody UserCredentilas userCredentilas) {
        return null;
    }
}
