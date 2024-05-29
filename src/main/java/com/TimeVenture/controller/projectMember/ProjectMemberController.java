package com.TimeVenture.controller.projectMember;

import com.TimeVenture.model.dto.projectMember.AddProjectMemberRequestDto;
import com.TimeVenture.model.dto.projectMember.ResponseProjectMemberDto;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.service.projectMember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projectMembers")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<ResponseProjectMemberDto> addProjectMember(@RequestBody AddProjectMemberRequestDto requestDto) {
        ProjectMember createdProjectMember = projectMemberService.addProjectMember(requestDto);
        ResponseProjectMemberDto response = new ResponseProjectMemberDto(
                createdProjectMember.getProjectMemberId(),
                createdProjectMember.getProject().getPid(),
                createdProjectMember.getMember().getEmail(),
                createdProjectMember.getAuth().toString()
        );
        return ResponseEntity.ok(response);
    }


}
