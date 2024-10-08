package com.TimeVenture.model;

import com.TimeVenture.model.dto.task.CreateTaskRequestDto;
import com.TimeVenture.model.dto.task.ResponseTaskDto;
import com.TimeVenture.model.dto.task.UpdateTaskRequestDto;
import com.TimeVenture.model.entity.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskModelMapper {
    TaskModelMapper INSTANCE = Mappers.getMapper(TaskModelMapper.class);

    Task toEntity(CreateTaskRequestDto requestDto);
    Task toEntity(UpdateTaskRequestDto requestDto);
    ResponseTaskDto toResponseDto(Task task);
    List<ResponseTaskDto> toResponseDtoList(List<Task> tasks);
}