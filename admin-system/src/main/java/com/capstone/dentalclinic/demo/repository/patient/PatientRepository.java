package com.capstone.dentalclinic.demo.repository.patient;

import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmailAddress(String email);

    Patient EmailAddress(String email);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Patient p 
            SET p.isEnable = TRUE WHERE p.emailAddress = ?1
            """)
    void enablePatientAccount(String emailAddress);


    @Query("""
            SELECT * FROM Patient p 
            WHERE p.emailAddress = ?1
            """)
    PatientDTO findByPatientEmailAddress(String email);

}
