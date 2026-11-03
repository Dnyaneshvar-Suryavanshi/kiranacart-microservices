package com.kiranacart.address.dto;

import com.kiranacart.address.enums.AddressLabel;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AddressResponseDto {

    private Long id;
    private AddressLabel label;
    private String fullName;
    private String phone;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String pincode;
    private Boolean isDefault;
    
}
