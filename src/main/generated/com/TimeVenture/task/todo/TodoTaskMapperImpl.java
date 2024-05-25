package com.TimeVenture.task.todo;

import com.TimeVenture.task.todo.TodoTask.TodoTaskBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T17:21:34+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class TodoTaskMapperImpl implements TodoTaskMapper {

    @Override
    public TodoTaskDto toDto(TodoTask todoTask) {
        if ( todoTask == null ) {
            return null;
        }

        TodoTaskDto todoTaskDto = new TodoTaskDto();

        todoTaskDto.setTId( todoTask.getTId() );
        todoTaskDto.setPId( todoTask.getPId() );
        todoTaskDto.setMId( todoTask.getMId() );
        todoTaskDto.setPMember( todoTask.getPMember() );
        todoTaskDto.setTitle( todoTask.getTitle() );
        todoTaskDto.setContent( todoTask.getContent() );
        todoTaskDto.setPriority( todoTask.getPriority() );
        todoTaskDto.setCreatedDate( todoTask.getCreatedDate() );
        todoTaskDto.setDueDate( todoTask.getDueDate() );
        todoTaskDto.setUpdatedDate( todoTask.getUpdatedDate() );

        return todoTaskDto;
    }

    @Override
    public TodoTask toEntity(TodoTaskDto todoTaskDto) {
        if ( todoTaskDto == null ) {
            return null;
        }

        TodoTaskBuilder<?, ?> todoTask = TodoTask.builder();

        todoTask.title( todoTaskDto.getTitle() );
        todoTask.content( todoTaskDto.getContent() );
        todoTask.createdDate( todoTaskDto.getCreatedDate() );
        todoTask.dueDate( todoTaskDto.getDueDate() );
        todoTask.updatedDate( todoTaskDto.getUpdatedDate() );
        todoTask.priority( todoTaskDto.getPriority() );

        return todoTask.build();
    }
}
