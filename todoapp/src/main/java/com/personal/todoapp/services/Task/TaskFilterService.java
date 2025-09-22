package com.personal.todoapp.services.Task;

import java.util.List;
import java.util.Arrays;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    enum SortOrder { 
        Ascending("asc"), 
        Descending("desc"); 
        
        SortOrder(String sortOrder) {
        }

        public static SortOrder fromString(String status) {
            return Arrays
            .stream(values())
            .filter(o -> o.toString().equalsIgnoreCase(status))
            .findAny()
            .orElseThrow();
        };
    };
    
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

    private PageRequest constructPageableRequest(TaskFilter taskFilter) {
        String sortColumn = 
            taskFilter.sortBy == null || taskFilter.sortBy.isEmpty() || taskFilter.sortBy.isBlank() ? 
            "taskName" : taskFilter.sortBy;

        SortOrder sortOrder = 
            taskFilter.sortOrder == null || taskFilter.sortOrder.isBlank() || taskFilter.sortOrder.isEmpty() ?
            SortOrder.Ascending : SortOrder.fromString(sortColumn);

        int pageSize = taskFilter.pageSize == 0 ? 10 : taskFilter.pageSize;    
        
        switch(sortOrder) {
            case Ascending : return PageRequest.of(taskFilter.page,pageSize,Sort.by(sortColumn).ascending());
            case Descending: return PageRequest.of(taskFilter.page,pageSize,Sort.by(sortColumn).descending()); 
            default : return null;
        }  
    }

    public List<TaskDto> filter(TaskFilter filter) {
        var spec = constructSpec(filter);
        var pageRequest = constructPageableRequest(filter);
        
        var page = pageRequest == null ? 
            taskRepository.findAll(spec) : taskRepository.findAll(spec,pageRequest).toList();
    
        return page.stream().map(taskMapper::fromEntity).toList();
    }
}
