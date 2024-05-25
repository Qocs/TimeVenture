package com.TimeVenture.task.done;

import com.TimeVenture.task.done.DoneTask.DoneTaskBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T17:21:34+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class DoneTaskMapperImpl implements DoneTaskMapper {

    @Override
    public DoneTaskDto toDto(DoneTask doneTask) {
        if ( doneTask == null ) {
            return null;
        }

        DoneTaskDto doneTaskDto = new DoneTaskDto();

        doneTaskDto.setTId( doneTask.getTId() );
        doneTaskDto.setPId( doneTask.getPId() );
        doneTaskDto.setMId( doneTask.getMId() );
        doneTaskDto.setPMember( doneTask.getPMember() );
        doneTaskDto.setTitle( doneTask.getTitle() );
        doneTaskDto.setContent( doneTask.getContent() );
        doneTaskDto.setPriority( doneTask.getPriority() );
        doneTaskDto.setCreatedDate( doneTask.getCreatedDate() );
        doneTaskDto.setDueDate( doneTask.getDueDate() );
        doneTaskDto.setUpdatedDate( doneTask.getUpdatedDate() );

        return doneTaskDto;
    }

    @Override
    public DoneTask toEntity(DoneTaskDto doneTaskDto) {
        if ( doneTaskDto == null ) {
            return null;
        }

        DoneTaskBuilder<?, ?> doneTask = DoneTask.builder();

        doneTask.title( doneTaskDto.getTitle() );
        doneTask.content( doneTaskDto.getContent() );
        doneTask.createdDate( doneTaskDto.getCreatedDate() );
        doneTask.dueDate( doneTaskDto.getDueDate() );
        doneTask.updatedDate( doneTaskDto.getUpdatedDate() );
        doneTask.priority( doneTaskDto.getPriority() );

        return doneTask.build();
    }
}
