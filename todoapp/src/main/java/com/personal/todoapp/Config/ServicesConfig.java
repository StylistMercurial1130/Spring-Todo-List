package com.personal.todoapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.personal.todoapp.services.TaskService;

@Configuration
public class ServicesConfig {
    
    @Bean
    public TaskService createTaskService() {
        return new TaskService();
    }
}
