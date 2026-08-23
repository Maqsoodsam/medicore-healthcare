package com.medicore.controller;

import com.medicore.model.Appointment;
import com.medicore.service.AppointmentService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> createAppointment(
            @Valid @RequestBody Appointment appointment) {

        return ResponseEntity.ok(
                appointmentService.createAppointment(appointment));
    }
}