package com.TimeVenture.model.dto.projectMember;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProjectMemberDto {

    private int projectMemberId; //클라이언트에게 반환해야하는 pk키
    private int projectId;
    private String memberId;
    private String auth;
}
