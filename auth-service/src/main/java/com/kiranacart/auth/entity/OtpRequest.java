package com.kiranacart.auth.entity;

import com.kiranacart.auth.enums.LoginChannel;
import com.kiranacart.auth.enums.OtpPurpose;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "otp_requests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OtpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LoginChannel channel;

    private String destination;   // the email or mobile number

    private String otpHash;       // never store the raw OTP

    @Enumerated(EnumType.STRING)
    private OtpPurpose purpose;

    private Instant expiresAt;
    private Instant verifiedAt;   // null until successfully verified
    private Integer attempts;

    private Instant createdAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        if (attempts == null) attempts = 0;
    }
}