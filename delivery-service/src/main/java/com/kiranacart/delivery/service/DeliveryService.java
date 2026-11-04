package com.kiranacart.delivery.service;

import com.kiranacart.delivery.dto.DeliveryQuoteRequestDto;
import com.kiranacart.delivery.dto.DeliveryQuoteResponseDto;
import com.kiranacart.delivery.entity.DeliveryZone;

import java.util.List;

public interface DeliveryService {

    DeliveryQuoteResponseDto calculateQuote(DeliveryQuoteRequestDto dto);
    List<DeliveryZone> getAllZones();

}