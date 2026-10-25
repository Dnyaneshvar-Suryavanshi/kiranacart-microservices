package com.kiranacart.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddToCartRequestDto {
    @NotNull
    private Long productId;

}
