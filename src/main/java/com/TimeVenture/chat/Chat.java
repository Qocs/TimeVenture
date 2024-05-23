package com.TimeVenture.chat;

import com.TimeVenture.project.Project;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chat {

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonManagedReference
    private Project project;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int cId;

    @Column
    private String cContent;

    @Column
    private Date cCreateDate;
}
