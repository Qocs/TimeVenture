package com.TimeVenture.member.service;

import com.TimeVenture.member.model.GoogleDetailResponse;
import com.TimeVenture.member.model.ApiResponse;

public interface GoogleService {
    ApiResponse<String> getGoogleLoginView();
    ApiResponse<GoogleDetailResponse> loginGoogle(String code);
}