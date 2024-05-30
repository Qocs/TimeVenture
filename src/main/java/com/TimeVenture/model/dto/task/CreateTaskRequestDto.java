package com.TimeVenture.model.dto.task;

import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.model.enums.Priority;
import com.TimeVenture.model.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequestDto {
    private Project pid;
    private Member mid;
    private ProjectMember pmember;
    private String title;
    private String content;
    private Priority priority;
    private TaskStatus taskStatus;

}