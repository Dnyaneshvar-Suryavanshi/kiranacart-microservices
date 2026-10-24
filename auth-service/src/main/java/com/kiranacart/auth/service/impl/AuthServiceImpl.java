package com.kiranacart.auth.service.impl;

import com.kiranacart.auth.dto.AuthResponseDto;
import com.kiranacart.auth.dto.OtpVerifyDto;
import com.kiranacart.auth.entity.Role;
import com.kiranacart.auth.entity.User;
import com.kiranacart.auth.repository.RoleRepository;
import com.kiranacart.auth.repository.UserRepository;
import com.kiranacart.auth.security.JwtUtil;
import com.kiranacart.auth.service.AuthService;
import com.kiranacart.auth.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OtpService otpService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;


    @Override
    public AuthResponseDto verifyAndLogin(OtpVerifyDto dto) {
        boolean valid = otpService.verifyOtp(dto);
        if(!valid){
            throw new IllegalArgumentException("Invalid or Expired OTP");
        }
        boolean isEmail = dto.getDestination().contains("@");

        User user = (isEmail ? userRepository.findByEmail(dto.getDestination()) : userRepository.findByMobile(dto.getDestination()))
                .orElseGet(() -> {
                    Role customerRole = roleRepository.findByName("CUSTOMER")
                    .orElseThrow(() -> new IllegalStateException("CUSTOMER role not seeded"));

                    User newUser = User.builder()
                            .email(isEmail ? dto.getDestination() : null)
                            .mobile(isEmail ? null : dto.getDestination())
                            .emailVerified(isEmail)
                            .mobileVerified(!isEmail)
                            .preferredLanguage("en")
                            .roles(Set.of(customerRole))
                            .build();

                    return userRepository.save(newUser);
                });
        String token = jwtUtil.generateToken(user);

        return AuthResponseDto.builder()
                .accessToken(token)
                .userId(user.getId())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }
}
