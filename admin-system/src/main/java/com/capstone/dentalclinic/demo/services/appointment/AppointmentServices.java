package com.capstone.dentalclinic.demo.services.appointment;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentServices {
    void saveAppointment(AppointmentDTO appointmentDto, Principal principal);

    void saveAppointmentPatient(AppointmentDTO appointmentDTO);

    List<Appointment> getAppointmentSchedule(Long id);

    Appointment checkIfTaken(Long id);

    List<Appointment> listOfAppointment(LocalDate localDate);

    LocalDate dateToday();

    Long countAppointmentToday();

    void cancelAppointment(Long id);

    Long countCancelledAppt();

    void deletePerId(Long id, String message);

    List<Appointment> findCancelledAppointment();

    void deleteAppointment(Long id);

    void addAppointmentSchedule(AppointmentDTO appointmentDTO);

    LocalDate date();
}
