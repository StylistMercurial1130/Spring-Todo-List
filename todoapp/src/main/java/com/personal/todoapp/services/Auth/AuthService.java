package com.personal.todoapp.services.Auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.personal.todoapp.Repository.UsersRepository;

public class AuthService {
    @Value("${secret}")
    private String token;

    private final String issuerName = AuthService.class.getName();
    private final UsersRepository usersRepository;    
    
    public AuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    public Optional<String> generateToken(String email) {
        if (usersRepository.findByEmailId(email).isEmpty()) {
            return Optional.empty();
        }

        var algorithm = Algorithm.HMAC256(token);

        return Optional.of(JWT.create()
        .withIssuer(issuerName)
        .withClaim("user", email)
        .withJWTId(UUID.randomUUID().toString())
        .sign(algorithm));
    }

    public Optional<DecodedJWT> verifyToken(String apiToken) {
        try {
            var algorithm = Algorithm.HMAC256(token);
            var jwtVerfier = JWT.require(algorithm)
                    .withIssuer(issuerName)
                    .withClaim("user", 
                        (claim, decodedJWT) -> 
                            usersRepository.findByEmailId(claim.asString()).isPresent())
                    .build();
            
            var jwtToken = jwtVerfier.verify(apiToken);

            return jwtToken == null ? Optional.empty() : Optional.of(jwtToken);
        } catch (Exception ex) {
            throw ex;        
        }
    } 
}
