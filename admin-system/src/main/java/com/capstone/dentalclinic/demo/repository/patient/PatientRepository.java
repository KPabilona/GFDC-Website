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
        @Query(value = """
                UPDATE Patient p 
                SET p.isEnable = TRUE WHERE p.emailAddress = ?1
                """)
        void enablePatientAccount(String emailAddress);


        @Transactional
        @Query(value = """
            SELECT p FROM Patient p 
            WHERE p.emailAddress = ?1
            """)
        Patient findByPatientEmailAddress(String email);

        // Patient Forgot Password
        @Transactional
        @Query("""
            SELECT p FROM Patient p
            WHERE p.emailAddress = ?1
            AND p.isEnable = true
        """)
        Optional<Patient> enabledEmailAddress(String email);      
        
        @Transactional
        @Query("""
                SELECT p.email_address, ptc.token 
                FROM patient p LEFT JOIN patient_token_confirmation ptc 
                ON p.id = ptc.id WHERE p.email_address = ?1
        """) 
        Patient selectPatientAndToken(String emailAddress);

}
