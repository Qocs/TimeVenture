package com.TimeVenture.task.hold;

import com.TimeVenture.task.hold.HoldTask.HoldTaskBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T17:21:34+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class HoldTaskMapperImpl implements HoldTaskMapper {

    @Override
    public HoldTaskDto toDto(HoldTask holdTask) {
        if ( holdTask == null ) {
            return null;
        }

        HoldTaskDto holdTaskDto = new HoldTaskDto();

        holdTaskDto.setTId( holdTask.getTId() );
        holdTaskDto.setPId( holdTask.getPId() );
        holdTaskDto.setMId( holdTask.getMId() );
        holdTaskDto.setPMember( holdTask.getPMember() );
        holdTaskDto.setTitle( holdTask.getTitle() );
        holdTaskDto.setContent( holdTask.getContent() );
        holdTaskDto.setPriority( holdTask.getPriority() );
        holdTaskDto.setCreatedDate( holdTask.getCreatedDate() );
        holdTaskDto.setDueDate( holdTask.getDueDate() );
        holdTaskDto.setUpdatedDate( holdTask.getUpdatedDate() );

        return holdTaskDto;
    }

    @Override
    public HoldTask toEntity(HoldTaskDto holdTaskDto) {
        if ( holdTaskDto == null ) {
            return null;
        }

        HoldTaskBuilder<?, ?> holdTask = HoldTask.builder();

        holdTask.title( holdTaskDto.getTitle() );
        holdTask.content( holdTaskDto.getContent() );
        holdTask.createdDate( holdTaskDto.getCreatedDate() );
        holdTask.dueDate( holdTaskDto.getDueDate() );
        holdTask.updatedDate( holdTaskDto.getUpdatedDate() );
        holdTask.priority( holdTaskDto.getPriority() );

        return holdTask.build();
    }
}
