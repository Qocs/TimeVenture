package com.TimeVenture.model.entity.projectMember;

import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.enums.Auth;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Getter
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_member", nullable = false)
    private Long projectMemberId;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonManagedReference
    private Project project;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    @ManyToOne
    @JoinColumn(name = "m_id")
    @JsonManagedReference
    private Member member;


}
