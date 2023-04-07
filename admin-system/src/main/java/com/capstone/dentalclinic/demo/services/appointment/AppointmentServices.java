package com.capstone.dentalclinic.demo.services.appointment;

import java.security.Principal;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;

public interface AppointmentServices {
    void saveAppointment(AppointmentDTO appointmentDto, Principal principal);

    Appointment getAppointmentSchedule(String emailAddress);
}
