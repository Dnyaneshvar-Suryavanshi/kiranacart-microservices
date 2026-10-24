package com.kiranacart.auth.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AuthResponseDto {

    private String accessToken;

    private Long userId;

    private List<String> roles;
}
