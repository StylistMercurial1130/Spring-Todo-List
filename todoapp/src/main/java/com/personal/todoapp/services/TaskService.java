package com.personal.todoapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.personal.todoapp.Models.Task;

public class TaskService {
    private final Queue<Task> tasks;

    public TaskService() {
        tasks = new ConcurrentLinkedDeque<>();
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }  

    public List<Task> getTasks(String taskName) {
        return tasks
        .stream()
        .filter(t -> t.taskName().equalsIgnoreCase(taskName))
        .toList();
    }

    public void removeTask(String taskName) {
        tasks.removeIf(t -> t.taskName().equalsIgnoreCase(taskName));
    }

    public List<Task> getAllTasks() {
        return tasks.stream().toList();
    }
}
