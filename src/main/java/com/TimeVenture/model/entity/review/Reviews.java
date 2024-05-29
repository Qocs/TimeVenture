package com.TimeVenture.model.entity.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column(name = "t_id")
    private int tid;

    @Column(name = "m_id")
    private String mid;

    private String content;

    @Column(name = "created_date")
    private Timestamp createdDate;

    // 외래키 제약 조건
    /*@ManyToOne
    @JoinColumn(name = "t_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "m_id")
    private Member member;*/
}