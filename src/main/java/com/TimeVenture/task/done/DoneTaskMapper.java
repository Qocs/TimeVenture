package com.TimeVenture.task.done;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoneTaskMapper {
    DoneTaskMapper INSTANCE = Mappers.getMapper(DoneTaskMapper.class);

    DoneTaskDto toDto(DoneTask doneTask);
    DoneTask toEntity(DoneTaskDto doneTaskDto);
}
