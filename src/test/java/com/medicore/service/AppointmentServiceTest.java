package com.medicore.service;

import com.medicore.exception.DoctorNotFoundException;
import com.medicore.exception.PatientNotFoundException;
import com.medicore.model.Appointment;
import com.medicore.model.Doctor;
import com.medicore.model.Patient;
import com.medicore.repository.AppointmentRepository;
import com.medicore.repository.DoctorRepository;
import com.medicore.repository.PatientRepository;
import com.medicore.service.impl.AppointmentServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    void shouldBookAppointmentSuccessfully() {

        Patient patient = new Patient(
                1L,
                "Sara Ahmed",
                "sara@example.com",
                "0412345678"
        );

        Doctor doctor = new Doctor(
                2L,
                "Dr Smith",
                "General Practice"
        );

        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));

        when(doctorRepository.findById(2L))
                .thenReturn(Optional.of(doctor));

        when(appointmentRepository.save(any(Appointment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Appointment result =
                appointmentService.bookAppointment(
                        1L,
                        2L,
                        "2026-09-10 10:00"
                );

        assertNotNull(result);
        assertEquals(patient, result.getPatient());
        assertEquals(doctor, result.getDoctor());
        assertEquals("BOOKED", result.getStatus());

        verify(appointmentRepository)
                .save(any(Appointment.class));
    }

    @Test
    void shouldThrowExceptionWhenPatientNotFound() {

        when(patientRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                PatientNotFoundException.class,
                () -> appointmentService.bookAppointment(
                        1L,
                        2L,
                        "2026-09-10 10:00"
                )
        );

        verify(appointmentRepository, never())
                .save(any(Appointment.class));
    }

    @Test
    void shouldThrowExceptionWhenDoctorNotFound() {

        Patient patient = new Patient(
                1L,
                "Sara Ahmed",
                "sara@example.com",
                "0412345678"
        );

        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));

        when(doctorRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(
                DoctorNotFoundException.class,
                () -> appointmentService.bookAppointment(
                        1L,
                        2L,
                        "2026-09-10 10:00"
                )
        );

        verify(appointmentRepository, never())
                .save(any(Appointment.class));
    }
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        patient = new Patient(
                1L,
                "Test Patient",
                "patient@test.com",
                "0400000000"
        );

        doctor = new Doctor(
                2L,
                "Test Doctor",
                "General Practice"
        );
    }
}
