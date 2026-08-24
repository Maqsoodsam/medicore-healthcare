package com.medicore.service.impl;

import com.medicore.exception.PatientNotFoundException;
import com.medicore.model.Patient;
import com.medicore.repository.PatientRepository;
import com.medicore.service.PatientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger =
            LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    public PatientServiceImpl(
            PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    @Override
    public Patient registerPatient(Patient patient) {

        logger.info(
                "Registering patient with email={}",
                patient.getEmail());

        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(
            Long id, Patient patient) {

        Patient existingPatient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new PatientNotFoundException(
                                        "Patient not found with ID: " + id));

        existingPatient.setName(patient.getName());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setPhoneNumber(
                patient.getPhoneNumber());

        return patientRepository.save(existingPatient);
    }

    @Override
    public Patient findPatientById(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() -> {

                    logger.warn(
                            "Patient lookup failed for id={}",
                            id);

                    return new PatientNotFoundException(
                            "Patient not found with ID: " + id);
                });
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}