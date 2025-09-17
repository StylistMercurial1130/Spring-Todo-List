package com.personal.todoapp.services;

import java.util.List;

import com.personal.todoapp.Models.Task;
import com.personal.todoapp.Repository.TaskRepository;

public class TaskService {
        
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }  

    public List<Task> getTasks(String taskName) {
        return taskRepository.findByTaskName(taskName);
    }

    public void removeTask(String taskName) {
        taskRepository.deleteByTaskName(taskName);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
