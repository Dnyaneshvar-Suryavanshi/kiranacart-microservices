package com.kiranacart.catalog.entity;

import com.kiranacart.catalog.enums.Unit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String title;
    private String description;
    private String sku;

    private BigDecimal price;
    private BigDecimal mrp;

    @Column(name = "stock_qty")
    private Integer stockQty;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private Boolean isActive;

    @Column(name = "created_at")
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        if(isActive == null) isActive = true;
    }

}
