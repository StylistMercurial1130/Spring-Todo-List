package com.personal.todoapp.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.personal.todoapp.Models.dto.UserDto;
import com.personal.todoapp.Models.entities.User;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User fromDto(UserDto userDto);
    UserDto fromEntity(User user);
}
