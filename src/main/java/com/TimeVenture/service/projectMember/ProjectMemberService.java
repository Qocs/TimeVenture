package com.TimeVenture.service.projectMember;

import com.TimeVenture.model.dto.projectMember.AddProjectMemberRequestDto;
import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.repository.member.MemberRepository;
import com.TimeVenture.repository.project.ProjecetRepository;
import com.TimeVenture.repository.projectMember.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjecetRepository projecetRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMember addProjectMember(AddProjectMemberRequestDto requestDto) {
        Project project = projecetRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProjectId: " + requestDto.getProjectId()));
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid MemberId: " + requestDto.getMemberId()));

        ProjectMember projectMember = requestDto.toEntity(project, member);
        return projectMemberRepository.save(projectMember);
    }

}
