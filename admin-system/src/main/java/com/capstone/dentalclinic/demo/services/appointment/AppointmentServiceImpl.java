package com.capstone.dentalclinic.demo.services.appointment;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

        System.out.println("THE PICK TIME 1 " + appointmentDto.getPickTime().getDisplayTime());
        System.out.println("THE PICK TIME 2 " + appointmentDto.getPickTime());

        ZoneId zoneId = ZoneId.of("Asia/Manila");
        DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("hh:mm a").withZone(zoneId);
        String formattedTime;
        formattedTime = FORMAT_TIME.format(LocalTime.of(11,00));
        System.out.println(" THE FORMATED TIME IS " + formattedTime);

        if(appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("ELEVEN_AM")){
            formattedTime = FORMAT_TIME.format(LocalTime.of(11,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        } else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("TWELVE_NOON")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(12,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("ONE_PM")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(13,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("TWO_PM")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(14,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("THREE_PM")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(15,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("FOUR_PM")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(16,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("FIVE_PM")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(17,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }else if (appointmentDto.getPickTime().getDisplayTime().equalsIgnoreCase("SIX_PM")) {
            formattedTime = FORMAT_TIME.format(LocalTime.of(18,00));

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setServices(appointmentDto.getServices());
            appointment.setDateAndTime(LocalDateTime.now());
            appointment.setPickDate(appointmentDto.getPickDate());
            appointment.setPickTime(formattedTime);

            appointmentRepository.save(appointment);
        }

    }
}
