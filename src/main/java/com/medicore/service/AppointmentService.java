package com.medicore.service;
import com.medicore.model.Appointment;
import java.util.List;


public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Long id, Appointment appointment);
    void cancelAppointment(Long id);
    List<Appointment> getAppointments();
}
