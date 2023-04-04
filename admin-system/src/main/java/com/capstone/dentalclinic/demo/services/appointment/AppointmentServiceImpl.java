package com.capstone.dentalclinic.demo.services.appointment;

import java.security.Principal;
import java.time.LocalDateTime;

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

        System.out.println("THE PRINCIPAL OUTPUT IS " + patient.toString());
        System.out.println("THE PICK TIME IS " + appointmentDto.getPickTime());

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setServices(appointmentDto.getServices());
        appointment.setDateAndTime(LocalDateTime.now());
        appointment.setPickDate(appointmentDto.getPickDate());
        appointment.setPickTime(appointmentDto.getPickTime());

        appointmentRepository.save(appointment);
    }
    
}
