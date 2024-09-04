package com.TimeVenture.model.entity.review;

import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "t_id")
    @JsonManagedReference
    private Task tid;

    @ManyToOne
    @JoinColumn(name = "m_id")
    @JsonManagedReference
    private Member mid;

    private String content;

    @Column(name = "created_date")
    private Timestamp createdDate;

}