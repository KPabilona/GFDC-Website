package com.capstone.dentalclinic.demo.services.appointment;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.DTO.CancelAppointment;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.CancelAppointmentTemplate;
import com.capstone.dentalclinic.demo.model.Status;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.repository.appointment.AppointmentRepository;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentServices {
    
    private final AppointmentRepository appointmentRepository;

    private final MailSender mailSender;
    private final CancelAppointmentTemplate cancelAppointmentTemplate;
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
        appointment.setIsTaken(true);

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
    public Long countAppointmentToday2() {
        return appointmentRepository.count();
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
}
