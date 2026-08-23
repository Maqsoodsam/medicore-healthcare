package com.medicore.service.impl;

import com.medicore.model.Patient;
import com.medicore.service.PatientService;
import org.springframework.stereotype.Service;

import com.medicore.exception.PatientNotFoundException;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public Patient registerPatient(Patient patient) {
        System.out.println("Patient registered: " + patient.getName());
        logger.info( "Registering patient with email={}", patient.getEmail());
        return patient;
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        System.out.println("Patient updated: " + id);
        patient.setId(id);
        return patient;
    }

    @Override public Patient findPatientById(Long id) {
        if (id == null || id <= 0) {
            logger.warn( "Patient lookup failed for id={}", id);
            throw new PatientNotFoundException( "Patient not found with ID: " + id );
        }
        return new Patient( id, "Sample Patient", "patient@example.com", "0412345678" );
    }

    @Override
    public List<Patient> getAllPatient() {
        return List.of(
                new Patient(
                        1L,
                        "Ali Khan",
                        "ali@example.com",
                        "0412345678"
                ),
                new Patient(
                        2L,
                        "Sara Ahmed",
                        "sara@example.com",
                        "0498765432"
                )
        );
    }



}