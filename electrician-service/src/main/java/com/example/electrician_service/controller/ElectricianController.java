package com.example.electrician_service.controller;

import com.example.electrician_service.model.Availability;
import com.example.electrician_service.model.Electrician;
import com.example.electrician_service.repository.ElectricianRepository;
import com.example.electrician_service.service.ElectricianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/electricians")
@RequiredArgsConstructor
public class ElectricianController {

    private final ElectricianRepository repository;
    private final ElectricianService service;

    @PostMapping
    public Electrician create(@RequestBody Electrician e) {
        e.setAvailability(Availability.AVAILABLE);
        return repository.save(e);
    }

    @GetMapping
    public List<Electrician> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Electrician getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Electrician update(@PathVariable Long id, @RequestBody Electrician e) {
        e.setId(id);
        return repository.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/available")
    public List<Electrician> available() {
        return repository.findAll()
                .stream()
                .filter(e -> e.getAvailability() == Availability.AVAILABLE)
                .toList();
    }

    @PutMapping("/job/{bookingId}/start")
    public void start(@PathVariable Long bookingId) {
        service.startJob(bookingId);
    }

    @PutMapping("/job/{bookingId}/complete")
    public void complete(@PathVariable Long bookingId) {
        service.completeJob(bookingId);
    }
}