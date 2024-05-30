package com.TimeVenture.model;

import com.TimeVenture.model.dto.task.CreateTaskRequestDto;
import com.TimeVenture.model.dto.task.ResponseTaskDto;
import com.TimeVenture.model.dto.task.UpdateTaskRequestDto;
import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.model.entity.task.Task.TaskBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T11:04:59+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TaskModelMapperImpl implements TaskModelMapper {

    @Override
    public Task toEntity(CreateTaskRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.pid( requestDto.getPid() );
        task.mid( requestDto.getMid() );
        task.pmember( requestDto.getPmember() );
        task.title( requestDto.getTitle() );
        task.content( requestDto.getContent() );
        task.taskStatus( requestDto.getTaskStatus() );
        task.priority( requestDto.getPriority() );

        return task.build();
    }

    @Override
    public Task toEntity(UpdateTaskRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.title( requestDto.getTitle() );
        task.content( requestDto.getContent() );
        task.dueDate( requestDto.getDueDate() );
        task.taskStatus( requestDto.getTaskStatus() );
        task.priority( requestDto.getPriority() );

        return task.build();
    }

    @Override
    public ResponseTaskDto toResponseDto(Task task) {
        if ( task == null ) {
            return null;
        }

        Task task1 = null;

        task1 = task;

        ResponseTaskDto responseTaskDto = new ResponseTaskDto( task1 );

        return responseTaskDto;
    }

    @Override
    public List<ResponseTaskDto> toResponseDtoList(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<ResponseTaskDto> list = new ArrayList<ResponseTaskDto>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( toResponseDto( task ) );
        }

        return list;
    }
}
