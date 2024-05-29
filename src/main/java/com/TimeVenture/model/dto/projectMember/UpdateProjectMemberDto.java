package com.TimeVenture.model.dto.projectMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProjectMemberDto {

    private int projectId;
    private int memberId;
    private String auth;
}
