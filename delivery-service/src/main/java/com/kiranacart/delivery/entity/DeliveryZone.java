package com.kiranacart.delivery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "delivery_zones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DeliveryZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pincode_prefix", nullable = false)
    private String pincodePrefix;

    @Column(name = "base_charge")
    private BigDecimal baseCharge;

    @Column(name = "free_delivery_above_amount")
    private BigDecimal freeDeliveryAboveAmount;

    @Column(name = "estimated_days")
    private Integer estimatedDays;
}
