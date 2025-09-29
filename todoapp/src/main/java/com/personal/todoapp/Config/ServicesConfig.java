package com.personal.todoapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.personal.todoapp.Models.mapper.TaskMapper;
import com.personal.todoapp.Repository.TaskRepository;
import com.personal.todoapp.Repository.UsersRepository;
import com.personal.todoapp.services.Auth.AuthService;
import com.personal.todoapp.services.Task.TaskFilterService;
import com.personal.todoapp.services.Task.TaskService;

@Configuration
public class ServicesConfig {
    
    @Bean
    public TaskService createTaskService(TaskRepository taskRepository,TaskMapper taskMapper) {
        return new TaskService(taskRepository,taskMapper);
    }

    @Bean
    public TaskFilterService createTaskFilterService(TaskRepository taskRepository, TaskMapper taskMapper) {
        return new TaskFilterService(taskRepository, taskMapper);
    }

    @Bean
    public AuthService createAuthService(UsersRepository usersRepository) {
        return new AuthService(usersRepository);
    }

    @Bean
    public JwtAuthenticationFilter createJwtAuthenticationFilter(AuthService authService) {
        return new JwtAuthenticationFilter(authService);
    }
}
