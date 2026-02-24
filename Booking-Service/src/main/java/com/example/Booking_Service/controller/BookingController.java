package com.example.Booking_Service.controller;


import com.example.Booking_Service.model.Booking;
import com.example.Booking_Service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Booking get(@PathVariable UUID id) {
        return bookingService.getBooking(id);
    }

    @PutMapping("/{id}/cancel")
    public Booking cancel(@PathVariable UUID id) {
        return bookingService.cancelBooking(id);
    }
}