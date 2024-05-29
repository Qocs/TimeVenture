package com.TimeVenture.controller.projectMember;

import com.TimeVenture.model.dto.projectMember.AddProjectMemberRequestDto;
import com.TimeVenture.model.dto.projectMember.ResponseProjectMemberDto;
import com.TimeVenture.model.dto.projectMember.UpdateProjectMemberDto;
import com.TimeVenture.model.entity.projectMember.ProjectMember;
import com.TimeVenture.service.projectMember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projectMember")
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

    @GetMapping("/{projectId}")
    public ResponseEntity<List<ResponseProjectMemberDto>> getAllProjectMembers(@PathVariable Long projectId) {
        List<ResponseProjectMemberDto> responseDtos = projectMemberService.findAllProjectMembers(projectId);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<ResponseProjectMemberDto> getProjectMember(@PathVariable Long projectId, @PathVariable String memberId) {
        ResponseProjectMemberDto responseDto = projectMemberService.findProjectMember(projectId, memberId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long projectId, @PathVariable String memberId) {
        projectMemberService.deleteProjectMember(projectId, memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<ResponseProjectMemberDto> updateProjectMember(
            @PathVariable Long projectId, @PathVariable String memberId, @RequestBody UpdateProjectMemberDto updateDto) {
        ResponseProjectMemberDto updateMember = projectMemberService.updateProjectMember(projectId, memberId, updateDto);
        return new ResponseEntity<>(updateMember, HttpStatus.OK);
    }
}
