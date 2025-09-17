package com.personal.todoapp.Models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id") private long id;

    @JsonProperty("taskName") private String taskName;

    @JsonProperty("taskDescription") private String taskDescription;

    @JsonProperty("priority") private int priority;


    public String taskName() { return taskName; }
    public String taskDescription() { return taskDescription; } 
    public int priority() { return priority; }

    public Task(String taskName,String taskDescription,int priority) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
    }

    public Task() {}

    @Override
    public String toString() {
        String displayFormat = "Task : [id : %d, taskName : %s, taskDescription: %s, priority : %d]";
        return String.format(displayFormat, id, taskName, taskDescription, priority);
    }
}