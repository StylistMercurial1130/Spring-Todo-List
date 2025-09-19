package com.personal.todoapp.Models.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.todoapp.Models.shared.TaskStatus;

public class TaskDto {
    @JsonProperty("taskId") public UUID taskId;
    @JsonProperty("taskName") public final String taskName;
    @JsonProperty("taskStatus") public final TaskStatus taskStatus; 
    @JsonProperty("taskSummary") public final String taskSummary;

    public TaskDto(String taskName,TaskStatus taskStatus,String taskSummary) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskSummary = taskSummary;
    }

    public TaskDto(UUID taskId,String taskName,TaskStatus taskStatus,String taskSummary) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskSummary = taskSummary;
    }
}
