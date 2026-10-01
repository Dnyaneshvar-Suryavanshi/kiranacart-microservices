package com.kiranacart.catalog.repository;

import com.kiranacart.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryIdAndIsActiveTrue(Long categoryId, Pageable pageable);
    @Query(value = "SELECT * FROM products where MATCH(title, description) AGAINST (?1) AND is_Active = true", nativeQuery = true)
    Page<Product> searchByKeyword(String keyword, Pageable pageable);
}
