package com.capstone.dentalclinic.demo.services.appointment;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.capstone.dentalclinic.demo.model.Status;
import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.repository.appointment.AppointmentRepository;
import com.capstone.dentalclinic.demo.services.patient.PatientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentServices {
    
    private final AppointmentRepository appointmentRepository;

    private final PatientService patientService;
    
    @Override
    public void saveAppointment(AppointmentDTO appointmentDto, Principal principal) {

        final Patient patient = patientService.findByEmailAddress(principal.getName());

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setServices(appointmentDto.getServices());
        appointment.setDateAndTime(LocalDateTime.now());
        appointment.setPickDate(appointmentDto.getPickDate());
        appointment.setPickTime(appointmentDto.getPickTime().getDisplayTime());
        appointment.setStatus(Status.APPROVED);

        appointmentRepository.save(appointment);

    }

    @Override
    public List<Appointment> getAppointmentSchedule(Long id) {
        return appointmentRepository.getAppointmentByPatientEmailAddress(id);
    }
}
