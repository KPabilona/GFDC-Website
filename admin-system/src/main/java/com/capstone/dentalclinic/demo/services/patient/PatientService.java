package com.capstone.dentalclinic.demo.services.patient;

import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.model.patient.Patient;

public interface PatientService {

boolean patientEmailAlreadyExist(String email);

void registerNewPatient(PatientDTO patientDTO);

boolean isMatchedPassword(PatientDTO patientDTO);
Patient findByEmailAddress(String emailAddress);

// Patient Forgot Password
boolean forgotPassword(String email);

String selectPatientAndToken(String emailAddress);

void setNewPasswordPatient(String password, String confirmPassword);

String patientTokenChecker(String token);

void setPatientNewPassword(String password);

}
