package com.kiranacart.auth.service;

import com.kiranacart.auth.dto.OtpRequestDto;
import com.kiranacart.auth.dto.OtpVerifyDto;

public interface OtpService {
    void requestOtp(OtpRequestDto dto);
    boolean verifyOtp(OtpVerifyDto dto);
}