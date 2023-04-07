package com.capstone.dentalclinic.demo.repository.appointment;

import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Transactional
    @Query("""
            SELECT a
            FROM Appointment a 
            LEFT JOIN Patient p 
            ON a.id = p.id 
            WHERE p.emailAddress = ?1
            """)
    Appointment getAppointmentByPatientEmailAddress(String emailAddress);
}

