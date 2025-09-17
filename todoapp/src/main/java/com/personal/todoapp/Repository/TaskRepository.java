package com.personal.todoapp.Repository;

import org.springframework.stereotype.Repository;
import com.personal.todoapp.Models.Task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task,UUID> {
    List<Task> findByTaskName(String taskName);
    void deleteByTaskName(String taskName);
}
