package com.TimeVenture.model.dto.member;

import com.TimeVenture.model.entity.member.Member;
import com.TimeVenture.model.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@Getter
public class AddMemberRequestDto {
    private String email;
    private String name;
    private String pwd;
    private String img;
    private String loginType;
    private String refreshToken;
    private Role role;

    }
