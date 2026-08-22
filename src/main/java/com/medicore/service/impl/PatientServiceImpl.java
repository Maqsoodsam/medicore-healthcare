package com.medicore.service.impl;

import com.medicore.model.Patient;
import com.medicore.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Override
    public Patient registerPatient(Patient patient) {
        System.out.println("Patient registered: " + patient.getName());
        return patient;
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        System.out.println("Patient updated: " + id);
        patient.setId(id);
        return patient;
    }

    @Override
    public Patient findPatientById(Long id) {
        return new Patient(id, "Sample Patient", "patient@example.com");
    }

    @Override
    public List<Patient> getAllPatient() {
        return List.of();
    }


}