package com.kiranacart.address.entity;

import com.kiranacart.address.enums.AddressLabel;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "addresses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private AddressLabel label;

    @Column(name = "full_name")
    private String fullName;

    private String phone;

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String pincode;

    @Column(name = "is_default")
    @Builder.Default
    private Boolean isDefault = false;
    
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
        if (isDefault == null) isDefault = false;
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = Instant.now();
    }
}