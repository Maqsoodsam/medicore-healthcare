package com.medicore.service;
import com.medicore.model.Patient;
import java.util.List;

public interface PatientService {
    Patient registerPatient(Patient patient);
    Patient updatePatient (Long id, Patient patient);
    Patient findPatientById(Long id);
    List<Patient> getAllPatient();


}
