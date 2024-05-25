package com.TimeVenture.task.doing;

import com.TimeVenture.task.doing.DoingTask.DoingTaskBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T17:21:34+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class DoingTaskMapperImpl implements DoingTaskMapper {

    @Override
    public DoingTaskDto toDto(DoingTask doingTask) {
        if ( doingTask == null ) {
            return null;
        }

        DoingTaskDto doingTaskDto = new DoingTaskDto();

        doingTaskDto.setTId( doingTask.getTId() );
        doingTaskDto.setPId( doingTask.getPId() );
        doingTaskDto.setMId( doingTask.getMId() );
        doingTaskDto.setPMember( doingTask.getPMember() );
        doingTaskDto.setTitle( doingTask.getTitle() );
        doingTaskDto.setContent( doingTask.getContent() );
        doingTaskDto.setPriority( doingTask.getPriority() );
        doingTaskDto.setCreatedDate( doingTask.getCreatedDate() );
        doingTaskDto.setDueDate( doingTask.getDueDate() );
        doingTaskDto.setUpdatedDate( doingTask.getUpdatedDate() );

        return doingTaskDto;
    }

    @Override
    public DoingTask toEntity(DoingTaskDto doingTaskDto) {
        if ( doingTaskDto == null ) {
            return null;
        }

        DoingTaskBuilder<?, ?> doingTask = DoingTask.builder();

        doingTask.title( doingTaskDto.getTitle() );
        doingTask.content( doingTaskDto.getContent() );
        doingTask.createdDate( doingTaskDto.getCreatedDate() );
        doingTask.dueDate( doingTaskDto.getDueDate() );
        doingTask.updatedDate( doingTaskDto.getUpdatedDate() );
        doingTask.priority( doingTaskDto.getPriority() );

        return doingTask.build();
    }
}
