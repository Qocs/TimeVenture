package com.TimeVenture.model.dto.task;

import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.model.enums.Priority;
import com.TimeVenture.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private int tid;
    private Project pid;  // project id
    private Member mid;  // member id
    private ProjectMember pmemberId;  // project member id
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp dueDate;
    private Timestamp updatedDate;
    private TaskStatus taskStatus;
    private Priority priority;

}
