package com.medicore.controller;

import com.medicore.model.Patient;
import com.medicore.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> registerPatient(
            @Valid @RequestBody Patient patient) {

        return ResponseEntity.ok(
                patientService.registerPatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                patientService.findPatientById(id));
    }
}