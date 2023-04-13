package com.capstone.dentalclinic.demo.services.appointment;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;

public interface AppointmentServices {
    void saveAppointment(AppointmentDTO appointmentDto, Principal principal);

    List<Appointment> getAppointmentSchedule(Long id);

    Appointment checkIfTaken(Long id);
}
