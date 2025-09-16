package com.personal.todoapp.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.todoapp.Models.Task;
import com.personal.todoapp.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
   
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
   
    @PostMapping("/add")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @GetMapping("/search")
    public List<Task> getTask(@RequestParam(defaultValue = "") String taskName) {
        return taskService.getTasks(taskName);
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/remove")
    public void removeTask(@RequestParam(defaultValue = "") String taskName) {
        taskService.removeTask(taskName);
    }
}
