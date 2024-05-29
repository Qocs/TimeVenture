package com.TimeVenture.model.entity.task;

import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.task.model.enums.Priority;
import com.TimeVenture.task.model.enums.TaskStatus;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int tid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName = "p_id")
    private Project project;

    @Column(name = "m_id")
    private String mid;

    @Column(name = "p_member")
    private Integer pmember;

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