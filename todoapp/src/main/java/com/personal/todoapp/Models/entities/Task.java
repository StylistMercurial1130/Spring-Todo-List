package com.personal.todoapp.Models.entities;

import java.io.Serializable;
import java.util.UUID;

import com.personal.todoapp.Models.shared.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task implements Serializable {
    @Id 
    private UUID taskId;

    private String taskName;
    private TaskStatus taskStatus;
    private String taskSummary;
    private int taskPriority;

    private static String displayFormat = "Task : [id : %s, taskName : %s, taskDescription: %s, priority : %d]";

    public String getTaskName() {
        return taskName;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getTaskSummary() {
        return taskSummary;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskSummary(String taskSummary) {
        this.taskSummary = taskSummary;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Task(String taskName,TaskStatus taskStatus,String taskSummary,int taskPriority) {
        this.taskId = UUID.randomUUID();
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskSummary = taskSummary;
        this.taskPriority = taskPriority;
    }
    
    public Task() {
        this.taskId = UUID.randomUUID();
    }
    
    public String toString() {
        return String.format(displayFormat,taskId.toString(),taskName,taskSummary,taskPriority);
    }
}