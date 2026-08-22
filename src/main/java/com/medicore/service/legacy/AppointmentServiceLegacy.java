package com.medicore.service.legacy;

import com.medicore.model.Appointment;

public class AppointmentServiceLegacy {

    public String processAppointment(Appointment appointment) {

        String result = "";

        if (appointment == null) {
            return "Appointment is invalid";
        }

        if (appointment.getPatientId() == null) {
            return "Patient ID is required";
        }

        if (appointment.getDoctorId() == null) {
            return "Doctor ID is required";
        }

        if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
            appointment.setStatus("BOOKED");
        }

        if (appointment.getStatus().equals("BOOKED")) {
            System.out.println("Appointment booked");
            result = "Appointment booked";
        } else if (appointment.getStatus().equals("CANCELLED")) {
            System.out.println("Appointment cancelled");
            result = "Appointment cancelled";
        } else if (appointment.getStatus().equals("COMPLETED")) {
            System.out.println("Appointment completed");
            result = "Appointment completed";
        } else {
            System.out.println("Unknown appointment status");
            result = "Unknown appointment status";
        }

        String temp = "MediCore";
        int x = 10;

        return result;
    }

    public String cancelAppointment(Appointment appointment) {

        if (appointment == null) {
            return "Appointment is invalid";
        }

        appointment.setStatus("CANCELLED");
        System.out.println("Appointment cancelled");

        return "Appointment cancelled";
    }
}
