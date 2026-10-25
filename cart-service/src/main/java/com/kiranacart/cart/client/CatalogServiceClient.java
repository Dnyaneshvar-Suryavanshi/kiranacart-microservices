package com.kiranacart.cart.client;

import com.kiranacart.cart.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATALOG-SERVICE")
public interface CatalogServiceClient {

    @GetMapping("/api/catalog/products/{id}")
    ProductResponseDto getProductById(@PathVariable("id") Long id);

}
