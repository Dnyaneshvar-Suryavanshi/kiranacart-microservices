package com.kiranacart.catalog.service;

import com.kiranacart.catalog.dto.ProductCreateRequestDto;
import com.kiranacart.catalog.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto create(ProductCreateRequestDto dto);
    ProductResponseDto getById(Long id);
    Page<ProductResponseDto> search(String keyword, Long categoryId, Pageable pageable);

}
