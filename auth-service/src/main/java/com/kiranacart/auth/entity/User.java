package com.kiranacart.auth.entity;

import com.kiranacart.auth.enums.LoginChannel;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    private Boolean emailVerified;
    private Boolean mobileVerified;

    @Enumerated(EnumType.STRING)
    private LoginChannel preferredLoginMethod;

    private String preferredLanguage;   // "en" | "hi" | "mr"

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private Instant createdAt;

    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
    }
}