package com.kiranacart.address.repository;

import com.kiranacart.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserId(Long userId);

    Optional<Address> findFirstByUserIdOrderByCreatedAtDesc(Long userId);

}
