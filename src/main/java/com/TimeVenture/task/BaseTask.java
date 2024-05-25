package com.TimeVenture.task;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tId;

    private int pId;
    private String mId;
    private Integer pMember;

    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp dueDate;
    private Timestamp updatedDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
