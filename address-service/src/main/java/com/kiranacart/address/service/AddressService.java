package com.kiranacart.address.service;

import com.kiranacart.address.dto.AddressRequestDto;
import com.kiranacart.address.dto.AddressResponseDto;

import java.util.List;

public interface AddressService {

    List<AddressResponseDto> getAllForUser(Long userId);
    AddressResponseDto create(Long userId, AddressRequestDto dto);
    AddressResponseDto update(Long addressId, AddressRequestDto dto);
    void delete(Long addressId);
    AddressResponseDto setDefault(Long addressId, Long userId);
    
}
