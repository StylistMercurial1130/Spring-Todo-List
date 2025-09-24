package com.personal.todoapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.personal.todoapp.Models.mapper.TaskMapper;
import com.personal.todoapp.Repository.TaskRepository;
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
    public AuthService createAuthService() {
        return new AuthService();
    }
}
