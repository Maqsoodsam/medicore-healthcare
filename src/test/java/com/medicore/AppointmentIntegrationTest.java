package com.medicore;

import com.medicore.model.Appointment;
import com.medicore.model.Doctor;
import com.medicore.model.Patient;
import com.medicore.repository.AppointmentRepository;
import com.medicore.repository.DoctorRepository;
import com.medicore.repository.PatientRepository;
import com.medicore.service.impl.AppointmentServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Test
    void shouldBookAndSaveAppointment() {

        // Create and save a patient
        Patient patient = new Patient(
                null,
                "Sara Ahmed",
                "sara@example.com",
                "0412345678"
        );

        patient = patientRepository.save(patient);

        // Create and save a doctor
        Doctor doctor = new Doctor(
                null,
                "Dr Smith",
                "General Practice"
        );

        doctor = doctorRepository.save(doctor);

        // Book an appointment
        Appointment appointment =
                appointmentService.bookAppointment(
                        patient.getId(),
                        doctor.getId(),
                        "2026-09-10 10:00"
                );

        // Check that the appointment was created
        assertNotNull(appointment);
        assertNotNull(appointment.getId());

        // Retrieve the appointment from the database
        Appointment savedAppointment =
                appointmentRepository
                        .findById(appointment.getId())
                        .orElseThrow();

        // Verify the stored information
        assertEquals(
                patient.getId(),
                savedAppointment.getPatient().getId()
        );

        assertEquals(
                doctor.getId(),
                savedAppointment.getDoctor().getId()
        );

        assertEquals(
                "BOOKED",
                savedAppointment.getStatus()
        );
    }
}
