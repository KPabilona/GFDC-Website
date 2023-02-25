package com.capstone.dentalclinic.demo.repository.patient;

import com.capstone.dentalclinic.demo.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmailAddress(String email);

    Patient EmailAddress(String email);
}
