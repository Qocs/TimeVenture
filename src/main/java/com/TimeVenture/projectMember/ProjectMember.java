package com.TimeVenture.projectMember;

import com.TimeVenture.project.Project;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class ProjectMember {

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonManagedReference
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String projectMember;

    @Enumerated(EnumType.STRING)
    private Auth auth;
}
