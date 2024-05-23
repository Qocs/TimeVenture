package com.TimeVenture.task.doing;

import com.TimeVenture.project.Project;
import com.TimeVenture.task.Priority;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DoingTask {

    @ManyToOne
    @JoinColumn(name = "pid")
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
}
