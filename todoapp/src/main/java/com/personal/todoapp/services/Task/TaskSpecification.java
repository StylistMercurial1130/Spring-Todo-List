package com.personal.todoapp.services.Task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.personal.todoapp.Models.dto.TaskFilter;
import com.personal.todoapp.Models.entities.Task;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TaskSpecification implements Specification<Task> {

    private final TaskFilter filter;

    public TaskSpecification(TaskFilter filter) {
        this.filter = filter;
    }

    private boolean filterFieldIsNullOrEmpty(String value) {
        return value != null && !(value.isBlank() || value.isEmpty());
    }

    @Override
    public Predicate toPredicate(
        @NonNull Root<Task> root, 
        @Nullable CriteriaQuery<?> query, 
        @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filterFieldIsNullOrEmpty(filter.taskName)) {
            predicates.add(criteriaBuilder.like(root.get("taskName"),filter.taskName));
        }

        if (filter.taskStatusValues != null && !filter.taskStatusValues.isEmpty()) {
            var inClause = criteriaBuilder.in(root.get("taskStatus"));    

            for (var status : filter.taskStatusValues) {
                inClause.value(status);
            }

            predicates.add(inClause);
        }
    
        if (filter.priorityValues != null && !filter.priorityValues.isEmpty()) {
            var inClause = criteriaBuilder.in(root.get("taskPriority"));

            for (var status : filter.priorityValues) {
                inClause.value(status);
            }
            
            predicates.add(inClause);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
