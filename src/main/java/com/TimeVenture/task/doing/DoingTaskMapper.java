package com.TimeVenture.task.doing;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoingTaskMapper {
    DoingTaskMapper INSTANCE = Mappers.getMapper(DoingTaskMapper.class);

    DoingTaskDto toDto(DoingTask doingTask);
    DoingTask toEntity(DoingTaskDto doingTaskDto);
}
