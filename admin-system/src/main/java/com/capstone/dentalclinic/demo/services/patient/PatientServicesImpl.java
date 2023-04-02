package com.capstone.dentalclinic.demo.services.patient;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplatePatient;
import com.capstone.dentalclinic.demo.model.Roles;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.model.patient.token.PatientTokenConfirmation;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.security.PasswordEncoder;

@Service
public class PatientServicesImpl implements UserDetailsService, PatientService{
    private final static String USER_NOT_FOUND_MSG =
            "User email %s not found";

    private final PatientRepository patientRepository;

    private final PatientTokenService patientTokenService;
    private final PasswordEncoder passwordEncoder;

    private final MailSender mailSender;

    private final EmailTemplatePatient emailTemplatePatient;

    public PatientServicesImpl(PatientRepository patientRepository,
                               PatientTokenService patientTokenService,
                               PasswordEncoder passwordEncoder,
                               EmailTemplatePatient emailTemplatePatient,
                               MailSender mailSender) {
        this.patientRepository = patientRepository;
        this.patientTokenService = patientTokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailTemplatePatient = emailTemplatePatient;
        this.mailSender = mailSender;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Patient check = patientRepository.findPatientByEmailAddress(email.toLowerCase());

        if(check.isEnable()) {
            UserDetails userDetails = User.withUsername(check.getEmailAddress().toLowerCase())
                    .password(check.getPatientPassword())
                    .roles("PATIENT")
                    .authorities("PATIENT")
                    .build();

            return userDetails;
        }
        System.out.println("ERROR MESSAGE FROM PRINTLN = " + String.format(USER_NOT_FOUND_MSG, email));
        throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
    }

    @Override
    public boolean patientEmailAlreadyExist(String email) {
        return patientRepository.findByEmailAddress(email).isPresent();
    }

    @Override
    public void registerNewPatient(PatientDTO patientDTO) {


            System.out.println("PATIENT DTO PASSWORD: " + patientDTO.getPatientPassword());
            System.out.println("PATIENT DTO CONFIRM PASSWORD: " + patientDTO.getConfirmPassword());
            System.out.println("PATIENT DTO EMAIL ADDRESS: " + patientDTO.getEmailAddress());

            final String encodedPassword = passwordEncoder.bcryptPasswordEncoder()
                    .encode(patientDTO.getPatientPassword());
            final String email = patientDTO.getEmailAddress().toLowerCase().toString();
            Patient patient = new Patient();
            patient.setFirstName(patientDTO.getFirstName());
            patient.setMiddleName(patientDTO.getMiddleName());
            patient.setLastName(patientDTO.getLastName());
            patient.setSuffix(patientDTO.getSuffix());
            patient.setContactNumber(patientDTO.getContactNumber());
            patient.setEmailAddress(email);
            patient.setHomeAddress(patientDTO.getHomeAddress());
            patient.setPatientPassword(encodedPassword);
            patient.setGender(patientDTO.getGender());
            patient.setBirthDate(patientDTO.getBirthDate());
            patient.setCivilStatus(patientDTO.getCivilStatus());
            patient.setPhysicalDisability(patientDTO.getPhysicalDisability());
            patient.setRoles(Roles.PATIENT);
            patient.setEnable(false);
            patient.setLocked(false);

            patientRepository.save(patient);

            // this is where we send tokens and emails for the newly registered patients
            final String token = UUID.randomUUID().toString();

            PatientTokenConfirmation tokenConfirmation =  new PatientTokenConfirmation(token,
                    LocalDateTime.now(), LocalDateTime.now().plusMinutes(60), patient);

            final String link = "http://localhost:8080/patient/confirm?tokens=" + token;

            System.out.println("PATIENT EMAIL ADDRESS: " + patient.getEmailAddress());
            System.out.println("PATIENT FIRST NAME: " + patient.getFirstName() );
            // this is where we email the patient for confirmation and to activate their account.
            mailSender.sendConfirmationMailPatient(patient.getEmailAddress(),
                    emailTemplatePatient.patientConfirmationRequest(patient.getFirstName(), link));

            patientTokenService.saveConfirmationToken(tokenConfirmation);

    }


    @Override
    public Patient findByEmailAddress(String emailAddress) {
        return patientRepository.findByPatientEmailAddress(emailAddress);
    }

    @Override
    public boolean isMatchedPassword(PatientDTO patientDTO) {
        return patientDTO.getConfirmPassword().equalsIgnoreCase(patientDTO.getPatientPassword());
    }

    @Override
    public boolean forgotPassword(String email) {
        return patientRepository.enabledEmailAddress(email).isPresent();
    }

    @Override
    public String selectPatientAndToken(String emailAddress) {

        return patientRepository.selectPatientAndToken(emailAddress);
    }

    @Override
    public void setNewPasswordPatient(String password, String confirmPassword) {
    }

    public String patientTokenChecker(String token) {
        if(patientRepository.checkToken(token) == null || patientRepository.checkToken(token).isEmpty()) {
            return "token/ExpiredToken";
        }
        setToken(token);
        return "PatientWebPages/NewPassword";
    }

    @Override
    public void setPatientNewPassword(String password) {
        final String  email = patientRepository.getEmailAddressByToken(getToken());
        final String encodePassword = passwordEncoder.bcryptPasswordEncoder().encode(password);
        patientRepository.setNewPasswordPatient(email, encodePassword);
    }
}
