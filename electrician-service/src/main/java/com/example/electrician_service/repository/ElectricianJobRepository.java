package com.example.electrician_service.repository;

import com.example.electrician_service.model.ElectricianJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectricianJobRepository extends JpaRepository<ElectricianJob, Long> {
    Optional<ElectricianJob> findByBookingId(Long bookingId);
}