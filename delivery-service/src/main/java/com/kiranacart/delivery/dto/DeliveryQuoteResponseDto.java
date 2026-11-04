package com.kiranacart.delivery.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class DeliveryQuoteResponseDto {

    private BigDecimal charge;
    private Integer estimatedDays;
    private Boolean freeDelivery;
}
