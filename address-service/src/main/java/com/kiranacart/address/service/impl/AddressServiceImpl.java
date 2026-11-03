package com.kiranacart.address.service.impl;

import com.kiranacart.address.dto.AddressRequestDto;
import com.kiranacart.address.dto.AddressResponseDto;
import com.kiranacart.address.entity.Address;
import com.kiranacart.address.repository.AddressRepository;
import com.kiranacart.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<AddressResponseDto> getAllForUser(Long userId) {
        return addressRepository.findByUserId(userId).stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public AddressResponseDto create(Long userId, AddressRequestDto dto) {
        boolean isFirstAddress = addressRepository.findByUserId(userId).isEmpty();

        Address address = Address.builder()
                .userId(userId)
                .label(dto.getLabel())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .line1(dto.getLine1())
                .line2(dto.getLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .pincode(dto.getPincode())
                .isDefault(isFirstAddress)
                .build();

        Address saved = addressRepository.save(address);
        return toResponseDto(saved);
    }

    @Override
    public AddressResponseDto update(Long addressId, AddressRequestDto dto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found: "+ addressId));
        address.setLabel(dto.getLabel());
        address.setFullName(dto.getFullName());
        address.setPhone(dto.getPhone());
        address.setLine1(dto.getLine1());
        address.setLine2(dto.getLine2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());

        Address saved = addressRepository.save(address);
        return toResponseDto(saved);
    }

    @Override
    public void delete(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found : "+ addressId));

        Long userId = address.getUserId();
        boolean wasDefault = address.getIsDefault();

        addressRepository.delete(address);

        if (wasDefault) {
            addressRepository.findFirstByUserIdOrderByCreatedAtDesc(userId)
            .ifPresent(nextAddress -> {
                nextAddress.setIsDefault(true);
                addressRepository.save(nextAddress);
            });
        }
    }

    @Override
    public AddressResponseDto setDefault(Long addressId, Long userId) {

        Address newDefault = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found : "+ addressId));

        if (!newDefault.getUserId().equals(userId)){
            throw new IllegalArgumentException("Address does not belong to this user");
        }

        addressRepository.findByUserId(userId).forEach(addr ->{
            if (addr.getIsDefault() && !addr.getId().equals(addressId)){
                addr.setIsDefault(false);
                addressRepository.save(addr);
            }
        });

        newDefault.setIsDefault(true);
        return toResponseDto(addressRepository.save(newDefault));
    }
    private AddressResponseDto toResponseDto(Address address) {
        return AddressResponseDto.builder()
                .id(address.getId())
                .label(address.getLabel())
                .fullName(address.getFullName())
                .phone(address.getPhone())
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .isDefault(address.getIsDefault())
                .build();
    }
}
