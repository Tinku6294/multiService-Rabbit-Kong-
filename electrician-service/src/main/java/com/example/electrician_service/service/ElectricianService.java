package com.example.electrician_service.service;

import com.example.electrician_service.model.Availability;
import com.example.electrician_service.model.Electrician;
import com.example.electrician_service.model.ElectricianJob;
import com.example.electrician_service.model.JobStatus;
import com.example.electrician_service.repository.ElectricianJobRepository;
import com.example.electrician_service.repository.ElectricianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ElectricianService {

    private final ElectricianRepository electricianRepository;
    private final ElectricianJobRepository jobRepository;

    @Transactional
    public ElectricianJob assignElectrician(Long bookingId) {

        Electrician electrician = electricianRepository
                .findFirstByAvailability(Availability.AVAILABLE)
                .orElseThrow(() -> new RuntimeException("No electricians available"));

        electrician.setAvailability(Availability.BUSY);
        electricianRepository.save(electrician);

        ElectricianJob job = ElectricianJob.builder()
                .bookingId(bookingId)
                .electricianId(electrician.getId())
                .status(JobStatus.ASSIGNED)
                .assignedAt(LocalDateTime.now())
                .build();

        return jobRepository.save(job);
    }

    @Transactional
    public void startJob(Long bookingId) {
        ElectricianJob job = jobRepository.findByBookingId(bookingId)
                .orElseThrow();
        job.setStatus(JobStatus.IN_PROGRESS);
    }

    @Transactional
    public void completeJob(Long bookingId) {
        ElectricianJob job = jobRepository.findByBookingId(bookingId)
                .orElseThrow();
        job.setStatus(JobStatus.COMPLETED);
        job.setCompletedAt(LocalDateTime.now());

        Electrician electrician = electricianRepository.findById(job.getElectricianId()).orElseThrow();
        electrician.setAvailability(Availability.AVAILABLE);
    }
}