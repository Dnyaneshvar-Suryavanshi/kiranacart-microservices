package com.kiranacart.address.controller;

import com.kiranacart.address.dto.AddressRequestDto;
import com.kiranacart.address.dto.AddressResponseDto;
import com.kiranacart.address.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAll(@RequestParam Long userId){
        return ResponseEntity.ok(addressService.getAllForUser(userId));
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> create(
            @RequestParam Long userId, @Valid @RequestBody AddressRequestDto dto){
        return ResponseEntity.ok(addressService.create(userId, dto));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDto> update(
            @PathVariable Long addressId, @Valid @RequestBody AddressRequestDto dto){
        return ResponseEntity.ok(addressService.update(addressId, dto));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(@PathVariable Long addressId){
        addressService.delete(addressId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{addressId}/default")
    public ResponseEntity<AddressResponseDto> setDefault(
            @PathVariable Long addressId,
            @RequestParam Long userId){
        return ResponseEntity.ok(addressService.setDefault(addressId, userId));
    }
}
