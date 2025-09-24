package com.personal.todoapp.services.Auth;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.algorithms.Algorithm;

public class AuthService {
    @Value("${auth.jwt.secret}")
    private String token;
    private Algorithm algorithm;

    public AuthService() {
        algorithm = Algorithm.HMAC256(token);
    } 
}
