package com.kiranacart.cart.service.impl;

import com.kiranacart.cart.client.CatalogServiceClient;
import com.kiranacart.cart.dto.AddToCartRequestDto;
import com.kiranacart.cart.dto.CartItemResponseDto;
import com.kiranacart.cart.dto.ProductResponseDto;
import com.kiranacart.cart.entity.CartItem;
import com.kiranacart.cart.repository.CartItemRepository;
import com.kiranacart.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final CatalogServiceClient catalogServiceClient;


    @Override
    public List<CartItemResponseDto> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public CartItemResponseDto addToCart(Long userId, AddToCartRequestDto dto) {
        ProductResponseDto product = catalogServiceClient.getProductById(dto.getProductId());

        if (product.getStockQty() == null || product.getStockQty() < 1){
            throw new IllegalArgumentException("Product is out of stock");
        }

        CartItem existing = cartItemRepository
                .findByUserIdAndProductId(userId, dto.getProductId())
                .orElse(null);
        if(existing != null){
            return increment(existing.getId());
        }

        CartItem newItem = CartItem.builder()
                .userId(userId)
                .productId(dto.getProductId())
                .priceSnapshot(product.getPrice())
                .build();

        CartItem saved = cartItemRepository.save(newItem);
        return toResponseDto(saved);
    }

    @Override
    public CartItemResponseDto increment(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found: "+ itemId));

        ProductResponseDto product = catalogServiceClient.getProductById(item.getProductId());

        if (item.getQuantity() + 1 > product.getStockQty()){
            throw new IllegalArgumentException("Only "+product.getStockQty() + " left in stock");
        }

        item.setQuantity(item.getQuantity() + 1);
        CartItem saved = cartItemRepository.save(item);
        return toResponseDto(saved);
    }

    @Override
    public void decrement(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found: "+ itemId));

        if (item.getQuantity() <= 1) {
            cartItemRepository.delete(item);
            return;
        }

        item.setQuantity(item.getQuantity() - 1);
        cartItemRepository.save(item);
    }

    @Override
    public void removeItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    private CartItemResponseDto toResponseDto(CartItem item){
        ProductResponseDto product = catalogServiceClient.getProductById(item.getProductId());

        return CartItemResponseDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .productTitle(product.getTitle())
                .quantity(item.getQuantity())
                .price(product.getPrice())
                .lineTotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .build();
    }
}
