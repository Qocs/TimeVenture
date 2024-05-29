package com.TimeVenture.service.projectMember;

import com.TimeVenture.model.dto.projectMember.AddProjectMemberRequestDto;
import com.TimeVenture.model.dto.projectMember.ResponseProjectMemberDto;
import com.TimeVenture.model.dto.projectMember.UpdateProjectMemberDto;
import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.entity.project.Project;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.model.enums.Auth;
import com.TimeVenture.repository.member.MemberRepository;
import com.TimeVenture.repository.project.ProjecetRepository;
import com.TimeVenture.repository.projectMember.ProjectMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjecetRepository projecetRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Transactional
    public ProjectMember addProjectMember(AddProjectMemberRequestDto requestDto) {
        Project project = projecetRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProjectId: " + requestDto.getProjectId()));
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid MemberId: " + requestDto.getMemberId()));

        ProjectMember projectMember = requestDto.toEntity(project, member);
        return projectMemberRepository.save(projectMember);
    }

    public List<ResponseProjectMemberDto> findAllProjectMembers(Long projectId) {
        Project project = projecetRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProjectId: " + projectId));

         List<ProjectMember> projectMembers = projectMemberRepository.findAllByProject(project);

        return projectMembers.stream().map(ResponseProjectMemberDto::new).collect(Collectors.toList());
    }

    public ResponseProjectMemberDto findProjectMember(Long projectId, String memberId) {
        Project project = projecetRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProjectId : " + projectId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memberId : " + memberId));

        ProjectMember projectMember = projectMemberRepository.findByProject(project, member)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project : " + project + "Invalid member : " + member));
        return new ResponseProjectMemberDto(projectMember);
    }

    @Transactional
    public void deleteProjectMember(Long projectId, String memberId) {
        Project project = projecetRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Invalid projetId" + projectId));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid memberId : " + memberId));
        ProjectMember projectMember = projectMemberRepository.findByProjectAndMember(project, member)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Project : " + project + "Invalid Member : " + member));

        projectMemberRepository.delete(projectMember);
    }

    public ResponseProjectMemberDto updateProjectMember(Long projectId, String memberId, UpdateProjectMemberDto updateDto) {
        Project project = projecetRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Invalid projectId : " + projectId));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid memberId : " + memberId));
        ProjectMember projectMember = projectMemberRepository.findByProjectAndMember(project, member)
                .orElseThrow(() -> new IllegalArgumentException("Invalid not found Project : " + project + ", Member : " + member));

        projectMember.setAuth(Auth.valueOf(updateDto.getAuth()));

        ProjectMember updatedProjectMember = projectMemberRepository.save(projectMember);

        return new ResponseProjectMemberDto(updatedProjectMember);
    }
}
