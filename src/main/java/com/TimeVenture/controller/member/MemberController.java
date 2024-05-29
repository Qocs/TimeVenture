package com.TimeVenture.controller.member;

import com.TimeVenture.model.dto.member.AddMemberRequestDto;
import com.TimeVenture.model.dto.member.ResponseMemberDto;
import com.TimeVenture.model.dto.member.UpdateMemberDto;
import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.service.member.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/{memberId}")
    public ResponseEntity<ResponseMemberDto> updateMember(@PathVariable String memberId, @RequestBody UpdateMemberDto updateDto) {
        ResponseMemberDto updatedMember = memberService.updateMember(memberId, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMember);
    }

    @PostMapping
    public ResponseEntity<ResponseMemberDto> addMember(@RequestBody AddMemberRequestDto addDto) {
        ResponseMemberDto member = memberService.createMember(addDto);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseMemberDto>> findAllMembers() {
        List<ResponseMemberDto> members = memberService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ResponseMemberDto> findMember(@PathVariable String memberId) {
        ResponseMemberDto member = memberService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
