package com.kiranacart.cart.controller;

import com.kiranacart.cart.dto.AddToCartRequestDto;
import com.kiranacart.cart.dto.CartItemResponseDto;
import com.kiranacart.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemResponseDto> addToCart(@RequestParam Long userId, @Valid @RequestBody AddToCartRequestDto dto){
        return ResponseEntity.ok(cartService.addToCart(userId, dto));
    }

    @PatchMapping("/items/{itemId}/increment")
    public ResponseEntity<CartItemResponseDto> increment(@PathVariable Long itemId){
        return ResponseEntity.ok(cartService.increment(itemId));
    }

    @PatchMapping("/items/{itemId}/decrement")
    public ResponseEntity<Void> decrement(@PathVariable Long itemId){
        cartService.decrement(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long itemId){
        cartService.removeItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
