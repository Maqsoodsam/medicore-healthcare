package com.medicore.service.impl;

import com.medicore.model.Appointment;
import com.medicore.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public Appointment createAppointment(Appointment appointment) {
        System.out.println("Appointment created");
        appointment.setStatus("BOOKED");
        return appointment;
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        System.out.println("Appointment updated: " + id);
        appointment.setId(id);
        return appointment;
    }

    @Override
    public void cancelAppointment(Long id) {
        System.out.println("Appointment cancelled: " + id);
    }

    @Override
    public List<Appointment> getAppointments() {
        return List.of(
                new Appointment(
                        1L,
                        1L,
                        1L,
                        "2026-08-25 10:00",
                        "BOOKED"
                )
        );
    }
}