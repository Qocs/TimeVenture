package com.TimeVenture.model.entity.task;

import com.TimeVenture.task.model.enums.Action;
import com.TimeVenture.task.model.enums.Priority;
import com.TimeVenture.task.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Table(name = "Task_Histories")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    @Column(name = "t_id")
    private int tid;

    @Column(name = "p_id")
    private int pid;
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

    private String modifiedBy;
    private Timestamp modifiedAt;
    
    @Enumerated(EnumType.STRING)
    private Action action;
}
