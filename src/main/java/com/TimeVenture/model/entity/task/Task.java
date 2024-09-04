package com.TimeVenture.model.entity.task;

import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.model.enums.Priority;
import com.TimeVenture.model.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Tasks")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @ManyToOne
    @JoinColumn(name = "p_id")
    @JsonManagedReference
    private Project pid;

    @OneToOne
    @JoinColumn(name = "m_id")
    @JsonManagedReference
    private Member mid;

    @OneToOne
    @JoinColumn(name = "p_member", nullable = true)
    @JsonManagedReference
    private ProjectMember pmember;

    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp dueDate;
    private Timestamp updatedDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private Priority priority;

}
