package com.TimeVenture.model.dto.task;

import com.TimeVenture.task.model.enums.Priority;
import com.TimeVenture.task.model.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequestDto {
    private int pid;
    private String mid;
    private Integer pmember;
    private String title;
    private String content;
    private Priority priority;
    private TaskStatus taskStatus;

}