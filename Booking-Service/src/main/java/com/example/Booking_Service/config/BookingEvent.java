package com.example.Booking_Service.config;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEvent {

    private UUID bookingId;
    private String customerName;
    private String serviceType;
    private String address;
    private String description;
    private String timeSlot;
    private LocalDateTime createdAt;
}