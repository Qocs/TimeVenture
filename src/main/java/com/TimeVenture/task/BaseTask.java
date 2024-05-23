package com.TimeVenture.task;

import com.TimeVenture.project.Project;
import com.TimeVenture.projectMember.ProjectMember;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Member;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseTask {
    @ManyToOne
    @JoinColumn(name = "p_id")
    @JsonManagedReference
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tId;

    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp dueDate;
    private Timestamp updatedDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "m_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "p_member")
    private ProjectMember projectMember;

    protected BaseTask(Project project, String title, String content, Timestamp createdDate, Timestamp dueDate, Timestamp updatedDate, Priority priority, Member member, ProjectMember projectMember) {
        this.project = project;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.updatedDate = updatedDate;
        this.priority = priority;
        this.member = member;
        this.projectMember = projectMember;
    }
}
