package com.TimeVenture.model.entity.member.dto;

import com.TimeVenture.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private String name;
    private Role role;
}
