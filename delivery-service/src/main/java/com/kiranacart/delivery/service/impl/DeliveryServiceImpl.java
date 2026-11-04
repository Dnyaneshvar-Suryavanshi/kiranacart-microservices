package com.kiranacart.delivery.service.impl;

import com.kiranacart.delivery.dto.DeliveryQuoteRequestDto;
import com.kiranacart.delivery.dto.DeliveryQuoteResponseDto;
import com.kiranacart.delivery.entity.DeliveryZone;
import com.kiranacart.delivery.repository.DeliveryZoneRepository;
import com.kiranacart.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    private static final int WEIGHT_BLOCK_GRAMS = 5000;
    private static final BigDecimal EXTRA_CHARGE_PER_BLOCK = new BigDecimal("20");

    private static final BigDecimal DEFAULT_BASE_CHARGE = new BigDecimal("60");
    private static final BigDecimal DEFAULT_FREE_ABOVE = new BigDecimal("999");
    private static final int DEFAULT_ESTIMATED_DAYS = 7;

    private final DeliveryZoneRepository zoneRepository;

    @Override
    public DeliveryQuoteResponseDto calculateQuote(DeliveryQuoteRequestDto dto) {
        String prefix = dto.getPincode().substring(0, 3);

        DeliveryZone zone = zoneRepository.findByPincodePrefix(prefix).orElse(null);

        BigDecimal baseCharge = (zone != null) ? zone.getBaseCharge() : DEFAULT_BASE_CHARGE;
        BigDecimal freeAbove = (zone != null) ? zone.getFreeDeliveryAboveAmount() : DEFAULT_FREE_ABOVE;
        int estimatedDays = (zone != null) ? zone.getEstimatedDays() : DEFAULT_ESTIMATED_DAYS;

        // Rule 1: cart value high enough means free delivery

        if (dto.getCartValue().compareTo(freeAbove) >= 0){
            return DeliveryQuoteResponseDto.builder()
                    .charge(BigDecimal.ZERO)
                    .estimatedDays(estimatedDays)
                    .freeDelivery(true)
                    .build();
        }

        // Rule 2: base charge plus a weight surcharge for heavy orders (mostly groceries)

        BigDecimal charge = baseCharge;
        int extraBlocks = dto.getCartWeightGrams() / WEIGHT_BLOCK_GRAMS;
        if (extraBlocks > 0){
            charge = charge.add(EXTRA_CHARGE_PER_BLOCK.multiply(BigDecimal.valueOf(extraBlocks)));
        }

        return DeliveryQuoteResponseDto.builder()
                .charge(charge)
                .estimatedDays(estimatedDays)
                .freeDelivery(false)
                .build();

    }

    @Override
    public List<DeliveryZone> getAllZones() {
        return zoneRepository.findAll();
    }
}
