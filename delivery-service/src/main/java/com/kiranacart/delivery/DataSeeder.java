package com.kiranacart.delivery;

import com.kiranacart.delivery.entity.DeliveryZone;
import com.kiranacart.delivery.repository.DeliveryZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner{

    private final DeliveryZoneRepository zoneRepository;

    @Override
    public void run(String... args){
        if (zoneRepository.count() > 0){
            return;
        }

        zoneRepository.save(DeliveryZone.builder()
                .pincodePrefix("400")
                .baseCharge(new BigDecimal("40"))
                .freeDeliveryAboveAmount(new BigDecimal("599"))
                .estimatedDays(3)
                .build());

        zoneRepository.save(DeliveryZone.builder()
                .pincodePrefix("411")   // Pune
                .baseCharge(new BigDecimal("30"))
                .freeDeliveryAboveAmount(new BigDecimal("499"))
                .estimatedDays(2)
                .build());

        zoneRepository.save(DeliveryZone.builder()
                .pincodePrefix("414")
                .baseCharge(new BigDecimal("50"))
                .freeDeliveryAboveAmount(new BigDecimal("799"))
                .estimatedDays(4)
                .build());

        System.out.println(">>> Seeded delivery zones successfully.");
    }
}
