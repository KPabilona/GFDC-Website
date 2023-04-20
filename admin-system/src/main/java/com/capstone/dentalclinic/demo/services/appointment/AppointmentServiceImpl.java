package com.capstone.dentalclinic.demo.services.appointment;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.AppointmentNotification;
import com.capstone.dentalclinic.demo.mail.email_template.CancelAppointmentTemplate;
import com.capstone.dentalclinic.demo.model.Status;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.repository.appointment.AppointmentRepository;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentServices {
    
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    private final MailSender mailSender;
    private final CancelAppointmentTemplate cancelAppointmentTemplate;

    private final AppointmentNotification appointmentNotification;
    private final PatientService patientService;
    
    @Override
    public void saveAppointment(AppointmentDTO appointmentDto, Principal principal) {

        final Patient patient = patientService.findByEmailAddress(principal.getName());

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setQueue(randomQueue());
        appointment.setServices(appointmentDto.getServices());
        appointment.setDateAndTime(LocalDateTime.now());
        appointment.setPickDate(appointmentDto.getPickDate());
        appointment.setPickTime(appointmentDto.getPickTime().getDisplayTime());
        appointment.setStatus(Status.APPROVED);
        appointment.setIsTaken(true);

        mailSender.appointmentNotification(patient.getEmailAddress(),
                appointmentNotification.appointmentNotification(patient.getFirstName(),patient.getLastName(),
                        appointmentDto.getPickDate(), appointmentDto.getPickTime().getDisplayTime(), randomQueue()));
        appointmentRepository.save(appointment);
    }

    @Override
    public void saveAppointmentPatient(AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId()).get();

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setServices(appointmentDTO.getServices());
        appointment.setPickTime(appointmentDTO.getPickTime().getDisplayTime());
        appointment.setDateAndTime(LocalDateTime.now());
        appointment.setPickDate(appointmentDTO.getPickDate());
        appointment.setQueue(randomQueue());
        appointment.setStatus(Status.APPROVED);
        appointment.setIsTaken(true);

        mailSender.appointmentNotification(patient.getEmailAddress(),
                appointmentNotification.appointmentNotification(patient.getFirstName(),patient.getLastName(),
                        appointmentDTO.getPickDate(), appointmentDTO.getPickTime().getDisplayTime(), randomQueue()));

        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentSchedule(Long id) {
        return appointmentRepository.getAppointmentByPatientEmailAddress(id);
    }

    @Override
    public Appointment checkIfTaken(Long id) {
        return appointmentRepository.checkIfTaken(id);
    }

    @Override
    public List<Appointment> listOfAppointment(LocalDate localDate) {
        return appointmentRepository.listOfAppointment(localDate);
    }

    @Override
    public LocalDate dateToday() {
        return LocalDate.now();
    }

    @Override
    public Long countAppointmentToday() {
        return appointmentRepository.appointmentToday(LocalDate.now());
    }

    @Override
    public void cancelAppointment(Long id) {
        appointmentRepository.cancelAppointment(id);
    }

    @Override
    public void deletePerId(Long id, String message) {
        Appointment appointment = appointmentRepository.selectById(id);

        mailSender.cancelAppointment(appointment.getPatient().getEmailAddress(),
                cancelAppointmentTemplate.cancelAppointment(appointment.getPatient().getFirstName(),
                        appointment.getPatient().getLastName(),
                        appointment.getPatient().getMiddleName(),
                        message, appointment.getPickDate(), appointment.getPickTime()));

        appointmentRepository.insertMessage(message, appointment.getId());

        appointmentRepository.isTakenFalse(appointment.getId());

    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteAppointmentById(id);
    }

    @Override
    public List<Appointment> findCancelledAppointment() {
        return appointmentRepository.findCancelledAppointment();
    }

    @Override
    public void addAppointmentSchedule(AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId()).get();

        Appointment appointmentSched = new Appointment();
        appointmentSched.setPatient(patient);
        appointmentSched.setQueue(randomQueue());
        appointmentSched.setServices(appointmentDTO.getServices());
        appointmentSched.setDateAndTime(LocalDateTime.now());
        appointmentSched.setPickDate(appointmentDTO.getPickDate());
        appointmentSched.setPickTime(appointmentDTO.getPickTime().getDisplayTime());
        appointmentSched.setStatus(Status.APPROVED);
        appointmentSched.setIsTaken(true);

        mailSender.appointmentNotification(patient.getEmailAddress(),
                appointmentNotification.appointmentNotification(patient.getFirstName(),patient.getLastName(),
                        appointmentDTO.getPickDate(), appointmentDTO.getPickTime().getDisplayTime(), randomQueue()));

        appointmentRepository.save(appointmentSched);
    }

    private final Integer randomQueue() {
        // Create a Random object
        Random random = new Random();

        // Generate a random integer between 1000 and 9999 (inclusive)
        int randomNumber = random.nextInt(9000) + 1000;

        return randomNumber;
    }

    @Override
    public Long countCancelledAppt() {
        return appointmentRepository.countCancelledAppt();
    }

    @Override
    public LocalDate date() {
        return LocalDate.now();
    }
}
