package com.kiranacart.cart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CartItemResponseDto {

    private Long id;
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal lineTotal;

}
