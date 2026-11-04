package com.kiranacart.delivery.repository;

import com.kiranacart.delivery.entity.DeliveryZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, Long> {

    Optional<DeliveryZone> findByPincodePrefix(String pincodePrefix);
}
