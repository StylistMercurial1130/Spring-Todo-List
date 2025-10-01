package com.personal.todoapp.Config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.todoapp.Exceptions.ErrorResponse;
import com.personal.todoapp.services.Auth.AuthService;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    public JwtAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");    
    
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
            
                var jwtToken = authService.verifyToken(token);
        
                if (jwtToken.isPresent()) {
                    var user = jwtToken.get().getClaim("user").asString();

                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(user, token,Collections.emptyList());
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
           } catch (Exception ex) {
                ErrorResponse errResponse = new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");

                ObjectMapper objMapper = new ObjectMapper();
                response.getWriter().write(objMapper.writeValueAsString(errResponse));

                return;
            }
       }

       filterChain.doFilter(request, response);
    }

}
