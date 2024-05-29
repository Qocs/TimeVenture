package com.TimeVenture.model.dto.projectMember;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProjectMemberDto {

    private Long projectMemberId; //클라이언트에게 반환해야하는 pk키
    private Long projectId;
    private String memberId;
    private String auth;
}
