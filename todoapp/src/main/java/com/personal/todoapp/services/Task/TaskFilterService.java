package com.personal.todoapp.services.Task;

import java.util.List;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.personal.todoapp.Models.dto.TaskDto;
import com.personal.todoapp.Models.dto.TaskFilter;
import com.personal.todoapp.Models.mapper.TaskMapper;
import com.personal.todoapp.Repository.TaskRepository;

public class TaskFilterService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    org.slf4j.Logger logger = LoggerFactory.getLogger(TaskFilterService.class);

    public TaskFilterService(TaskRepository taskRepository,TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    enum SortOrder { 
        Ascending("asc"), 
        Descending("desc"); 
        
        private final String sortOrder;
        
        SortOrder(String sortOrder) {
            this.sortOrder = sortOrder;
        }

        public static SortOrder fromString(String order) {
            return Arrays
            .stream(values())
            .filter(o -> o.getSortOrder().equalsIgnoreCase(order))
            .findAny()
            .orElseThrow();
        };
    
        public String getSortOrder() {
            return this.sortOrder;
        }
    };
    
    private PageRequest constructPageableRequest(TaskFilter taskFilter) {
        String sortColumn = 
            taskFilter.sortBy == null || taskFilter.sortBy.isEmpty() || taskFilter.sortBy.isBlank() ? 
            "taskName" : taskFilter.sortBy;

        logger.info("sort order {}",taskFilter.sortOrder);
        SortOrder sortOrder = 
            taskFilter.sortOrder == null || taskFilter.sortOrder.isBlank() || taskFilter.sortOrder.isEmpty() ?
            SortOrder.Ascending : SortOrder.fromString(taskFilter.sortOrder);

        int pageSize = taskFilter.pageSize == 0 ? 10 : taskFilter.pageSize;  
        int page = taskFilter.page;  
        
        switch(sortOrder) {
            case Ascending : return PageRequest.of(page,pageSize,Sort.by(sortColumn).ascending());
            case Descending: return PageRequest.of(page,pageSize,Sort.by(sortColumn).descending()); 
            default : return null;
        }  
    }

    public List<TaskDto> filter(TaskFilter filter) {
        var spec = new TaskSpecification(filter);
        var pageRequest = constructPageableRequest(filter);
        
        var page = pageRequest == null ? 
            taskRepository.findAll(spec) : taskRepository.findAll(spec,pageRequest).toList();
    
        return page.stream().map(taskMapper::fromEntity).toList();
    }
}
