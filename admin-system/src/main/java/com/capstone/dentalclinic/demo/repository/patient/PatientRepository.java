package com.capstone.dentalclinic.demo.repository.patient;

import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                SELECT ptc.token 
                FROM Patient p LEFT JOIN PatientTokenConfirmation ptc 
                ON p.id = ptc.id WHERE p.emailAddress = ?1
        """)
        String selectPatientAndToken(String emailAddress);

        @Transactional
        @Query("""
                SELECT  t.token FROM PatientTokenConfirmation t 
                WHERE t.token = ?1 AND t.confirmedAt IS NOT NULL
                """)
        String checkToken(String token);

        @Transactional
        @Query("""
                SELECT p.emailAddress 
                FROM Patient p 
                LEFT JOIN PatientTokenConfirmation ptc 
                ON p.id = ptc.id WHERE 
                ptc.token = ?1
                """)
        String getEmailAddressByToken(String token);

        @Modifying
        @Transactional
        @Query("""
                UPDATE Patient p
                SET p.password = ?2
                WHERE  p.emailAddress = ?1
                """)
        void setNewPasswordPatient(String email, String password);
}
