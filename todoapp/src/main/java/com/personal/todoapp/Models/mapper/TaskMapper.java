package com.personal.todoapp.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.personal.todoapp.Models.dto.TaskDto;
import com.personal.todoapp.Models.entities.Task;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    public Task fromDto(TaskDto task);
    public TaskDto fromEntity(Task task);
}
