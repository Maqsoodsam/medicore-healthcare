package com.medicore.service.impl;

import com.medicore.exception.DoctorNotFoundException;
import com.medicore.model.Doctor;
import com.medicore.repository.DoctorRepository;
import com.medicore.service.DoctorService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(
            DoctorRepository doctorRepository) {

        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {

        Doctor existingDoctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new DoctorNotFoundException(
                                        "Doctor not found with ID: " + id));

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialisation(
                doctor.getSpecialisation());

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public List<String> getDoctorSchedule(Long doctorId) {

        doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID: " + doctorId));

        return List.of(
                "Monday 9:00 AM - 12:00 PM",
                "Wednesday 1:00 PM - 4:00 PM"
        );
    }
}