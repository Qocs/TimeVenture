package com.TimeVenture.task.hold;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HoldTaskMapper {
    HoldTaskMapper INSTANCE = Mappers.getMapper(HoldTaskMapper.class);

    HoldTaskDto toDto(HoldTask holdTask);
    HoldTask toEntity(HoldTaskDto holdTaskDto);
}
