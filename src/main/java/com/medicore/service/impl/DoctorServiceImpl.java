package com.medicore.service.impl;

import com.medicore.model.Doctor;
import com.medicore.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        System.out.println("Doctor registered: " + doctor.getName());
        return doctor;
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        System.out.println("Doctor updated: " + id);
        doctor.setId(id);
        return doctor;
    }

    @Override
    public List<String> getDoctorSchedule(Long doctorId) {
        return List.of(
                "Monday 9:00 AM - 12:00 PM",
                "Wednesday 1:00 PM - 4:00 PM"
        );
    }
}