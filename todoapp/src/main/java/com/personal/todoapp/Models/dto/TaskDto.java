package com.personal.todoapp.Models.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.todoapp.Models.shared.TaskStatus;

public class TaskDto implements Serializable {
    @JsonProperty("taskId") public UUID taskId;
    @JsonProperty("taskName") public final String taskName;
    @JsonProperty("taskStatus") public final TaskStatus taskStatus; 
    @JsonProperty("taskSummary") public final String taskSummary;
    @JsonProperty("taskPriority") public final int taskPriority;

    public TaskDto(UUID taskId,String taskName,TaskStatus taskStatus,String taskSummary,int taskPriority) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskSummary = taskSummary;
        this.taskPriority = taskPriority;
    }

    public String toString() {
        return String.format("TaskDto : [id : %s, taskName : %s, taskDescription: %s, priority : %d]",taskId,taskName,taskSummary,taskPriority);
    }
}
