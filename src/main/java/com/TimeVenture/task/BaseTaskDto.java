package com.TimeVenture.task;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public abstract class BaseTaskDto {
    private int tId;
    private int pId;
    private String mId;
    private int projectMember;
    private String title;
    private String content;
    private Priority priority;
    private Timestamp createdDate;
    private Timestamp dueDate;
    private Timestamp updatedDate;
}
