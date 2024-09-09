package com.TimeVenture.model.entity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String message;
    private String accessToken;
    private String refreshToken;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }
}
