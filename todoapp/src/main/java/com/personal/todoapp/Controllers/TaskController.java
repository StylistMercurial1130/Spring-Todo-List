package com.personal.todoapp.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.todoapp.Exceptions.RequestException;
import com.personal.todoapp.Exceptions.TaskNotFoundException;
import com.personal.todoapp.Models.dto.TaskDto;
import com.personal.todoapp.services.TaskService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/app")
public class TaskController {

    private final TaskService taskService;
    Logger logger = LoggerFactory.getLogger(TaskController.class);
   
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
   
    @PostMapping("/task")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto task) {
        if (task == null) {
            throw new RequestException("request body is empty or null !");
        }

        return ResponseEntity.ok(taskService.addTask(task));
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") UUID id) {
        if (id == null) {
            throw new RequestException("request body is empty or null !");
        }

        Optional<TaskDto> task = taskService.findTaskById(id);
    
        if (task.isEmpty()) {
            throw new TaskNotFoundException(String.format("task with id %s not found !",id.toString()));
        } else {
            return ResponseEntity.ok(task.get());
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }    
}
