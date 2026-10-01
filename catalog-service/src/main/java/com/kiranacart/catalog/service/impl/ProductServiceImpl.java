package com.kiranacart.catalog.service.impl;

import com.kiranacart.catalog.dto.ProductCreateRequestDto;
import com.kiranacart.catalog.dto.ProductResponseDto;
import com.kiranacart.catalog.entity.Category;
import com.kiranacart.catalog.entity.Product;
import com.kiranacart.catalog.repository.CategoryRepository;
import com.kiranacart.catalog.repository.ProductRepository;
import com.kiranacart.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDto create(ProductCreateRequestDto dto){
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found" + dto.getCategoryId()));

        Product product = Product.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(category)
                .price(dto.getPrice())
                .stockQty(dto.getStockQty())
                .build();

        Product saved = productRepository.save(product);
        return toResponseDto(saved);
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found" + id));
        return toResponseDto(product);
    }

    @Override
    public Page<ProductResponseDto> search(String keyword, Long categoryId, Pageable pageable) {
        Page<Product> products = (keyword != null && !keyword.isBlank())
                ? productRepository.searchByKeyword(keyword, pageable)
                : productRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable);
        return products.map(this::toResponseDto);
    }
    private ProductResponseDto toResponseDto(Product p) {
        return ProductResponseDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .price(p.getPrice())
                .mrp(p.getMrp())
                .stockQty(p.getStockQty())
                .categoryName(p.getCategory().getName())
                .build();
    }

}
