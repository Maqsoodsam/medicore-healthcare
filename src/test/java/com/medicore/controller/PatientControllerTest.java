package com.medicore.controller;

import com.medicore.exception.PatientNotFoundException;
import com.medicore.model.Patient;
import com.medicore.service.PatientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;


    // Test 1: Valid patient registration
    @Test
    void shouldRegisterPatientSuccessfully() throws Exception {

        Patient patient = new Patient(
                1L,
                "Sara Ali",
                "sara@example.com",
                "0412345678"
        );

        when(patientService.registerPatient(any(Patient.class)))
                .thenReturn(patient);

        String requestJson = """
                {
                    "name": "Sara Ali",
                    "email": "sara@example.com",
                    "phoneNumber": "0412345678"
                }
                """;

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sara Ali"))
                .andExpect(jsonPath("$.email").value("sara@example.com"));
    }


    // Test 2: Invalid patient data
    @Test
    void shouldReturnBadRequestForInvalidPatient() throws Exception {

        String invalidPatient = """
                {
                    "name": "",
                    "email": "sara@example.com",
                    "phoneNumber": "0412345678"
                }
                """;

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPatient))
                .andExpect(status().isBadRequest());
    }


    // Test 3: Patient not found
    @Test
    void shouldReturnNotFoundWhenPatientDoesNotExist() throws Exception {

        when(patientService.findPatientById(25L))
                .thenThrow(new PatientNotFoundException(
                        "Patient not found with ID: 25"
                ));

        mockMvc.perform(get("/patients/25"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message")
                        .value("Patient not found with ID: 25"))
                .andExpect(jsonPath("$.path")
                        .value("/patients/25"))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}