package com.kiranacart.auth.repository;

import com.kiranacart.auth.entity.OtpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRequestRepository extends JpaRepository<OtpRequest, Long> {
    Optional<OtpRequest> findTopByDestinationOrderByCreatedAtDesc(String destination);
}
