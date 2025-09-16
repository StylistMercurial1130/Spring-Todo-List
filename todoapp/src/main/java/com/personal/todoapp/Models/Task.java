package com.personal.todoapp.Models;

public record Task(int priority,String taskName,String taskDescription) {
    public String toString() {
        return String.format("Task : [name : %s, priority : %d, description : %s]",taskName,priority,taskDescription);
    }
}
