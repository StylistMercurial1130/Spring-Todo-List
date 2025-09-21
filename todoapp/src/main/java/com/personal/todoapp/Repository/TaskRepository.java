package com.personal.todoapp.Repository;

import org.springframework.stereotype.Repository;

import com.personal.todoapp.Models.entities.Task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface TaskRepository extends JpaRepository<Task,UUID>, JpaSpecificationExecutor<Task> {
    List<Task> findByTaskName(String taskName);
    Task findByTaskId(UUID taskId); 
    void deleteByTaskName(String taskName);
}
