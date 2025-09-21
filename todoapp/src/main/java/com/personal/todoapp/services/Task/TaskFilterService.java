package com.personal.todoapp.services.Task;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.personal.todoapp.Models.dto.TaskDto;
import com.personal.todoapp.Models.dto.TaskFilter;
import com.personal.todoapp.Models.entities.Task;
import com.personal.todoapp.Models.mapper.TaskMapper;
import com.personal.todoapp.Repository.TaskRepository;

public class TaskFilterService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskFilterService(TaskRepository taskRepository,TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    private Specification<Task> constructSpec(TaskFilter filter) {
        Specification<Task> spec = (root, query, cb) -> null;
        
        if (filter.taskName != null) {
            spec.and((root,query,cb) -> {
                return cb.like(root.get("taskName"),filter.taskName);
            });
        }

        if (filter.taskStatusValues != null && !filter.taskStatusValues.isEmpty()) {
            spec.and((root,query,cb) -> {
                return root.get("taskStatus").in(filter.taskStatusValues);
            });
        }

        if (filter.priorityValues != null && !filter.priorityValues.isEmpty()) {
            spec.and((root,query,cb) -> {
                return root.get("taskPriority").in(filter.priorityValues);
            });
        }

        return spec;
    }

    public List<TaskDto> filter(TaskFilter filter) {
        var spec = constructSpec(filter);
    
        return this.taskRepository
        .findAll(spec)
        .stream()
        .map(taskMapper::fromEntity)
        .toList();
    }
}
