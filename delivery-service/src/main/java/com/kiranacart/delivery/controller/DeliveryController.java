package com.kiranacart.delivery.controller;

import com.kiranacart.delivery.dto.DeliveryQuoteRequestDto;
import com.kiranacart.delivery.dto.DeliveryQuoteResponseDto;
import com.kiranacart.delivery.entity.DeliveryZone;
import com.kiranacart.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/quote")
    public ResponseEntity<DeliveryQuoteResponseDto> quote(@Valid @RequestBody DeliveryQuoteRequestDto dto){
        return ResponseEntity.ok(deliveryService.calculateQuote(dto));
    }

    @GetMapping("/zones")
    public ResponseEntity<List<DeliveryZone>> getZones(){
        return ResponseEntity.ok(deliveryService.getAllZones());
    }
}
