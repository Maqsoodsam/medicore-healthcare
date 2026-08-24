package com.medicore.service.impl;
import org.springframework.transaction.annotation.Transactional;
import com.medicore.model.Patient;
import com.medicore.model.Doctor;
import com.medicore.exception.PatientNotFoundException;
import com.medicore.exception.DoctorNotFoundException;
import com.medicore.exception.AppointmentNotFoundException;
import com.medicore.model.Appointment;
import com.medicore.repository.AppointmentRepository;
import com.medicore.repository.PatientRepository;
import com.medicore.repository.DoctorRepository;
import com.medicore.service.AppointmentService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl
        implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Appointment createAppointment(
            Appointment appointment) {

        if (appointment.getStatus() == null
                || appointment.getStatus().isBlank()) {

            appointment.setStatus("BOOKED");
        }

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(
            Long id, Appointment appointment) {

        Appointment existingAppointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new AppointmentNotFoundException(
                                        "Appointment not found with ID: " + id));

        existingAppointment.setPatient(
                appointment.getPatient());

        existingAppointment.setDoctor(
                appointment.getDoctor());

        existingAppointment.setAppointmentDate(
                appointment.getAppointmentDate());

        existingAppointment.setStatus(
                appointment.getStatus());

        return appointmentRepository.save(
                existingAppointment);
    }

    @Override
    public void cancelAppointment(Long id) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new AppointmentNotFoundException(
                                        "Appointment not found with ID: " + id));

        appointment.setStatus("CANCELLED");

        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }
    @Transactional
    public Appointment bookAppointment( Long patientId, Long doctorId, String appointmentDate) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException( "Patient not found with ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId) .orElseThrow(() -> new   DoctorNotFoundException( "Doctor not found with ID: " + doctorId));
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus("BOOKED");
        return appointmentRepository.save(appointment); }
}