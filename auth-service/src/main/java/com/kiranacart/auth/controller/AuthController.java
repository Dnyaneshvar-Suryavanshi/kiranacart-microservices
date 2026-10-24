package com.kiranacart.auth.controller;

import com.kiranacart.auth.dto.AuthResponseDto;
import com.kiranacart.auth.dto.OtpRequestDto;
import com.kiranacart.auth.dto.OtpVerifyDto;
import com.kiranacart.auth.service.AuthService;
import com.kiranacart.auth.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OtpService otpService;
    private final AuthService authService;

    @PostMapping("/otp/request")
    public ResponseEntity<Void> requestOtp(@Valid @RequestBody OtpRequestDto dto) {
        otpService.requestOtp(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<AuthResponseDto> verifyOtp(@Valid @RequestBody OtpVerifyDto dto){
        return ResponseEntity.ok(authService.verifyAndLogin(dto));
    }
}
