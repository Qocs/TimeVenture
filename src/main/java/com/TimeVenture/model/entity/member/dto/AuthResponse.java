package com.TimeVenture.model.entity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String message;
    private String jwtToken;
}
