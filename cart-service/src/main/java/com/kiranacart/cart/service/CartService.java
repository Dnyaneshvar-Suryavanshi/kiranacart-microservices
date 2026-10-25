package com.kiranacart.cart.service;

import com.kiranacart.cart.dto.AddToCartRequestDto;
import com.kiranacart.cart.dto.CartItemResponseDto;

import java.util.List;

public interface CartService {

    List<CartItemResponseDto> getCart(Long userId);
    CartItemResponseDto addToCart(Long userId, AddToCartRequestDto dto);
    CartItemResponseDto increment(Long itemId);
    void decrement(Long itemId);
    void removeItem(Long itemId);

}
