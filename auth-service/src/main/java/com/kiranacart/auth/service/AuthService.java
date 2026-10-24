package com.kiranacart.auth.service;

import com.kiranacart.auth.dto.AuthResponseDto;
import com.kiranacart.auth.dto.OtpVerifyDto;

public interface AuthService {

    AuthResponseDto verifyAndLogin(OtpVerifyDto dto);
}
