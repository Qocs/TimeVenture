package com.TimeVenture.service.member;

import com.TimeVenture.model.dto.member.AddMemberRequestDto;
import com.TimeVenture.model.dto.member.ResponseMemberDto;
import com.TimeVenture.model.dto.member.UpdateMemberDto;
import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseMemberDto updateMember(String memberId, UpdateMemberDto updateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid memberId : " + memberId));
        member.update(updateDto.getName(), updateDto.getImg(), updateDto.getRole());
        member.setRegDate(new Timestamp(System.currentTimeMillis()));

        Member updatedMember = memberRepository.save(member);
        return new ResponseMemberDto(updatedMember);
    }

    @Transactional
    public ResponseMemberDto createMember(AddMemberRequestDto addDto) {
        Member member = Member.builder().email(addDto.getEmail())
                .name(addDto.getName())
                .pwd(addDto.getPwd())
                .img(addDto.getImg())
                .loginType(addDto.getLoginType())
                .refreshToken(addDto.getRefreshToken())
                .role(addDto.getRole()).build();

        Member savedMember = memberRepository.save(member);
        return new ResponseMemberDto(savedMember);
    }

    @Transactional
    public void deleteMember(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("Invalid MemberId : " + memberId);
        }
        memberRepository.deleteById(memberId);
    }

    public List<ResponseMemberDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(ResponseMemberDto::new).collect(Collectors.toList());
    }

    public ResponseMemberDto getMember(String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid MemberId : " + memberId));
        return new ResponseMemberDto(member);
    }
}
