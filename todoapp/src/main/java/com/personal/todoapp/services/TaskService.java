package com.personal.todoapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;

import com.personal.todoapp.Models.dto.TaskDto;
import com.personal.todoapp.Models.entities.Task;
import com.personal.todoapp.Models.mapper.TaskMapper;
import com.personal.todoapp.Repository.TaskRepository;

public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository,TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDto addTask(TaskDto task) {
        logger.info("Adding task : {}", task);

        var taskEntity = taskMapper.fromDto(task);
        return taskMapper.fromEntity(taskRepository.save(taskEntity));
    }

    public Optional<TaskDto> findTaskById(UUID id) {
        if (id == null) return Optional.empty();
    
        Task task = taskRepository.findByTaskId(id);
        if (task == null)  return Optional.empty();
        
        return Optional.of(taskMapper.fromEntity(task));
    }

    public List<TaskDto> getTasks(String taskName) {
        return 
            taskRepository
            .findByTaskName(taskName)
            .stream()
            .map(taskMapper::fromEntity)
            .toList();
    }

    public void removeTask(String taskName) {
        taskRepository.deleteByTaskName(taskName);
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository
        .findAll().stream()
        .map(taskMapper::fromEntity)
        .toList();
    }
}
