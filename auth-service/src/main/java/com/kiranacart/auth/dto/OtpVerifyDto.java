package com.kiranacart.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OtpVerifyDto {

    @NotNull
    private String destination;

    @NotBlank
    private String otp;

}
