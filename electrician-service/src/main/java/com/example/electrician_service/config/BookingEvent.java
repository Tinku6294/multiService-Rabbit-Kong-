package com.example.electrician_service.config;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingEvent {

    private Long bookingId;
    private String customerName;
    private String serviceType;
    private String address;
    private String description;
    private String timeSlot;
    private String status;
    private LocalDateTime createdAt;
}