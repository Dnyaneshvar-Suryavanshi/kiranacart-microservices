package com.kiranacart.catalog.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal mrp;
    private Integer stockQty;
    private String categoryName;
    
}
