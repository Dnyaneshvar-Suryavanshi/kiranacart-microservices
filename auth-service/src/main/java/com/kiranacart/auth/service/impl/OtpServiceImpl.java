package com.kiranacart.auth.service.impl;

import com.kiranacart.auth.dto.OtpRequestDto;
import com.kiranacart.auth.dto.OtpVerifyDto;
import com.kiranacart.auth.entity.OtpRequest;
import com.kiranacart.auth.enums.OtpPurpose;
import com.kiranacart.auth.repository.OtpRequestRepository;
import com.kiranacart.auth.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRequestRepository otpRequestRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void requestOtp(OtpRequestDto dto) {
        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));

        OtpRequest otpRequest = OtpRequest.builder()
                .channel(dto.getChannel())
                .destination(dto.getDestination())
                .otpHash(passwordEncoder.encode(otp))
                .purpose(OtpPurpose.LOGIN)
                .expiresAt(Instant.now().plus(5, ChronoUnit.MINUTES))
                .build();

        otpRequestRepository.save(otpRequest);

        System.out.println(">>> OTP for " + dto.getDestination() + " is: "+otp);
    }

    @Override
    public boolean verifyOtp(OtpVerifyDto dto) {
        OtpRequest latest = otpRequestRepository.findTopByDestinationOrderByCreatedAtDesc(dto.getDestination())
                .orElse(null);

        if(latest == null){
            return false;
        }

        if(latest.getVerifiedAt() != null){
            return false;
        }

        if(Instant.now().isAfter(latest.getExpiresAt())){
            return false;
        }

        boolean matches = passwordEncoder.matches(dto.getOtp(), latest.getOtpHash());

        if(matches){
            latest.setVerifiedAt(Instant.now());
            otpRequestRepository.save(latest);
        }
        return matches;
    }
}
