package com.medicore.service;
import com.medicore.model.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor registerDoctor(Doctor doctor);
    Doctor updateDoctor (Long id, Doctor doctor);
    List<String> getDoctorSchedule(Long doctorId);
}


