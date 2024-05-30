package com.TimeVenture.model.dto.member;

import com.TimeVenture.model.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@Getter
public class ResponseMemberDto {

    private String name;
    private Timestamp regDate;
    private String img;

    public ResponseMemberDto(Member member) {
        this.name = member.getName();
        this.regDate = member.getRegDate();
        this.img = member.getImg();
    }
}
