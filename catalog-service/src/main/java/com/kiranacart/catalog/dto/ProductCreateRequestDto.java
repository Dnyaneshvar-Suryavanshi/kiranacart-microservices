package com.kiranacart.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
public class ProductCreateRequestDto {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Long categoryId;

    @NotNull @Positive
    private BigDecimal price;

    @NotNull @PositiveOrZero
    private Integer stockQty;

}
