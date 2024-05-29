package com.TimeVenture.model.dto.task;

import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.task.Task;
import com.TimeVenture.task.model.enums.Priority;
import com.TimeVenture.task.model.enums.TaskStatus;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ResponseTaskDto {
    private int tid;
    private Project pid;
    private String mid;
    private Integer pmember;
    private String title;
    private String content;
    private Priority priority;
    private TaskStatus taskStatus;
    private Timestamp createdDate;
    private Timestamp dueDate;
    private Timestamp updatedDate;

    public ResponseTaskDto(Task task) {
        this.tid = task.getTid();
        this.pid = task.getProject();
        this.mid = task.getMid();
        this.pmember = task.getPmember();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.priority = task.getPriority();
        this.taskStatus = task.getTaskStatus();
        this.createdDate = task.getCreatedDate();
        this.dueDate = task.getDueDate();
        this.updatedDate = task.getUpdatedDate();
    }
}