package com.personal.todoapp.Models.dto;

import java.util.List;

import com.personal.todoapp.Models.shared.TaskStatus;

public class TaskFilter {
    public final int page;
    public final int pageSize;
    public final String taskName;
    public final List<TaskStatus> taskStatusValues;
    public final List<Integer> priorityValues;

    public TaskFilter(int page,int pageSize,String taskName,List<TaskStatus> taskStatusVaues,List<Integer> priorityValues) {
        this.page = page;
        this.pageSize = pageSize;
        this.taskName = taskName;
        this.taskStatusValues = taskStatusVaues;
        this.priorityValues = priorityValues;
    }
}
