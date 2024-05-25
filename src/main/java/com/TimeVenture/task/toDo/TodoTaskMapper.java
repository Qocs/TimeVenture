package com.TimeVenture.task.todo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoTaskMapper{
    TodoTaskMapper INSTANCE = Mappers.getMapper(TodoTaskMapper.class);

    TodoTaskDto toDto(TodoTask todoTask);
    TodoTask toEntity(TodoTaskDto todoTaskDto);
}
