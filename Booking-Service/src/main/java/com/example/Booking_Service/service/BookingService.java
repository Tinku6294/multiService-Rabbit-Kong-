package com.example.Booking_Service.service;


import com.example.Booking_Service.config.BookingEvent;
import com.example.Booking_Service.config.BookingEventPublisher;
import com.example.Booking_Service.model.Booking;
import com.example.Booking_Service.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private  BookingEventPublisher publisher;
    public Booking createBooking(Booking booking) {

        booking.setStatus("CREATED");
        booking.setCreatedAt(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);

        BookingEvent event = BookingEvent.builder()
                .bookingId(saved.getId())
                .customerName(saved.getCustomerName())
                .serviceType(saved.getServiceType())
                .address(saved.getAddress())
                .description(saved.getDescription())
                .timeSlot(saved.getTimeSlot())
                .createdAt(saved.getCreatedAt())
                .build();

        publisher.publishBookingCreated(event);

        return saved;
    }

    public Booking getBooking(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking cancelBooking(UUID id) {
        Booking booking = getBooking(id);
        booking.setStatus("CANCELLED");
        return bookingRepository.save(booking);
    }
}