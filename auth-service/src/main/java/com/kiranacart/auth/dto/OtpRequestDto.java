package com.kiranacart.auth.dto;

import com.kiranacart.auth.enums.LoginChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OtpRequestDto {

    @NotNull
    private LoginChannel channel;

    @NotBlank
    private String destination;

}
