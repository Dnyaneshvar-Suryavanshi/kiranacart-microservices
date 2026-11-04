package com.kiranacart.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class DeliveryQuoteRequestDto {

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;

    @NotNull
    @PositiveOrZero
    private Integer cartWeightGrams;

    @NotNull
    @PositiveOrZero
    private BigDecimal cartValue;
}
