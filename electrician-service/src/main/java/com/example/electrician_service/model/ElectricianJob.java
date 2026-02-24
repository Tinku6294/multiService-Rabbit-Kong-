package com.example.electrician_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElectricianJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private Long electricianId;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private LocalDateTime assignedAt;
    private LocalDateTime completedAt;
}