package com.TimeVenture.model.entity.file;

import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="attachments")
@Getter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int fileId;

    @ManyToOne
    @JoinColumn(name = "m_id")
    @JsonManagedReference
    private Member mid;

    @ManyToOne
    @JoinColumn(name = "p_id")
    @JsonManagedReference
    private Member pid;

    @ManyToOne
    @JoinColumn(name = "t_id")
    @JsonManagedReference
    private Task tid;

    private String fileName;
    private String fileUrl;
    private Timestamp regDate;
}
