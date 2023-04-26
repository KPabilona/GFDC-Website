package com.capstone.dentalclinic.demo.repository.appointment;

import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
            WHERE a.isTaken = true
            """)
    List<Appointment> getAllAppointment();


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
            AND a.isTaken = true
            """)
    List<Appointment> listOfAppointment(LocalDate localDate);

    @Transactional
    @Query("""
            SELECT COUNT(a)
            FROM Appointment a
            WHERE a.pickDate = ?1
            AND a.isTaken = true
            """)
    Long appointmentToday(LocalDate localDate);

    @Transactional
    @Query("""
            SELECT COUNT(a)
            FROM Appointment a
            WHERE a.isTaken = false
            """)
    Long countCancelledAppt();

    @Transactional
    @Modifying
    @Query("""
            UPDATE Appointment a
            SET a.isTaken = false 
            WHERE a.id = ?1
            """)
    void cancelAppointment(Long id);
    @Transactional
    @Query("""
            SELECT a
            FROM Appointment a
            WHERE a.id = ?1
            """)
    Appointment selectById(Long id);

    @Transactional
    @Modifying
    @Query("""
            DELETE FROM Appointment a
            WHERE a.id = ?1
            """)
    void deleteAppointmentById(Long id);

    @Transactional
    @Query("""
            SELECT a
            FROM Appointment a
            WHERE a.isTaken = false
            """)
    List<Appointment> findCancelledAppointment();

    @Transactional
    @Modifying
    @Query("""
            UPDATE Appointment a
            SET a.message = ?1
            WHERE a.id = ?2
            """)
    void insertMessage(String message, Long id);
    @Transactional
    @Modifying
    @Query("""
            UPDATE Appointment a
            SET a.isTaken = false
            WHERE a.id = ?1
            """)
    void isTakenFalse(Long id);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Appointment a
            SET a.status = CANCELLED
            WHERE a.id = ?1
            """)
    void setStatusCancelled(Long id);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Appointment a
            SET a.toothNumber = ?1
            WHERE a.id = ?2
            """)
    void setToothNumber(String toothNumber, Long id);

}

