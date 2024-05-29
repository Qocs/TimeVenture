package com.TimeVenture.model.dto.member;

import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@Getter
public class ResponseMemberDto {

    private String name;
    private String img;
    private String role;
    private Timestamp regDate;

    public ResponseMemberDto(Member member) {
        this.name = member.getName();
        this.img = member.getImg();
        this.role = member.getRole().toString();
        this.regDate = member.getRegDate();
    }
}
