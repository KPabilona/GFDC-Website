package com.capstone.dentalclinic.demo.repository.appointment;

import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Transactional
    @Query("""
            SELECT a
            FROM Appointment a
            WHERE patient.id = ?1
            """)
    List<Appointment> getAppointmentByPatientEmailAddress(Long id);

    @Transactional
    @Query("""
            SELECT a 
            FROM Appointment a 
            WHERE patient.id = ?1
            """)
    Appointment checkIfTaken(Long id);

    @Transactional
    @Query("""
            SELECT a
            FROM Appointment a
            WHERE a.pickDate = ?1
            """)
    List<Appointment> listOfAppointment(LocalDate localDate);

    @Transactional
    @Query("""
            SELECT a 
            FROM Appointment a 
            WHERE a.pickDate = ?1
            """)
    Long appointmentToday(LocalDate localDate);
}

