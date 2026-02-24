package com.example.electrician_service.repository;

import com.example.electrician_service.model.Availability;
import com.example.electrician_service.model.Electrician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectricianRepository extends JpaRepository<Electrician, Long> {
    Optional<Electrician> findFirstByAvailability(Availability availability);
}