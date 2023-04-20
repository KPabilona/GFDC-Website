package com.capstone.dentalclinic.demo.services.administrator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.model.administrator.Employee;
import com.capstone.dentalclinic.demo.model.Roles;
import com.capstone.dentalclinic.demo.model.administrator.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.repository.administrator.EmployeeRepository;
import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import com.capstone.dentalclinic.demo.mail.email_template.EmailTemplate;

import lombok.AllArgsConstructor;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements UserDetailsService, AdminService {
    private final static String USER_NOT_FOUND_MSG =
    "User email %s not found";

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailTemplate emailTemplate;
    private final AdminTokenService adminTokenService;

    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Employee check = employeeRepository.findEmployeeByEmailAddress(email);

        if(check.isEnable()) {
            UserDetails userDetails = User.withUsername(check.getEmailAddress())
                            .password(check.getPassword())
                            .authorities("ADMIN")
                            .roles("ADMIN")
                            .build();
            return userDetails;
        }else {
            System.out.println("ERROR MESSAGE FROM PRINTLN = " + String.format(USER_NOT_FOUND_MSG, email));
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    // Get all The data in the UserModel database
    @Override
    public List<Employee> getAllUser() {
        return employeeRepository.findAll();
    }

    // get user by Id
    @Override
    public Employee getUserById(Long id) {
        Optional<Employee> userModelOptional = employeeRepository.findById(id);

        if(userModelOptional.isPresent()) {
            return userModelOptional.get();
        }
        return null;
    }

    // This will Update existing UserData
    @Override
    public Employee updateUser(Long id, Employee userModel) {
        Optional<Employee> userModelOptional = employeeRepository.findById(id);
        
        if(userModelOptional.isPresent()) {
            return employeeRepository.save(userModel);         
        }
        return null;
    }


    public void registerNewEmployee(EmployeeDTO employeeDTO) {

        String encode = passwordEncoder.bcryptPasswordEncoder()
                        .encode(employeeDTO.getEmployeePassword());

        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employeeDTO.getFirstName());
        newEmployee.setMiddleName(employeeDTO.getMiddleName());
        newEmployee.setLastName(employeeDTO.getLastName());
        newEmployee.setEmailAddress(employeeDTO.getEmailAddress());
        newEmployee.setEmployeePassword(encode);
        newEmployee.setAddress(employeeDTO.getAddress());
        newEmployee.setGender(employeeDTO.getGender());
        newEmployee.setContactNumber(employeeDTO.getContactNumber());
        newEmployee.setRoles(Roles.ADMIN);
        // For Deployment
//        newEmployee.setBirthDate(employeeDTO.getBirthDate().plusDay(1);
        newEmployee.setBirthDate(employeeDTO.getBirthDate());
        newEmployee.setMaritalStatus(employeeDTO.getMaritalStatus());
        newEmployee.setEnable(false);
        newEmployee.setLocked(false);
        employeeRepository.save(newEmployee);

        // Creating a Token before saving the employee
        String token = UUID.randomUUID().toString();

        // For Deployment

//      ConfirmationToken confirmationToken = new ConfirmationToken(token,
//              LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusMinutes(30), newEmployee);

        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), newEmployee);


        final String link = "http://localhost:8080/admin/confirm?tokens=" + token;

        // For Deployment
        final String link2 = "http://gfdcph.com/admin/confirm?tokens=" + token;
        mailSender.sendConfirmationMail(newEmployee.getEmailAddress(),
                emailTemplate.adminValidation(newEmployee.getFirstName(), newEmployee.getLastName(),
                        newEmployee.getEmailAddress(), newEmployee.getAddress(), newEmployee.getContactNumber(), link));

        adminTokenService.saveConfirmationToken(confirmationToken);
    }


    @Override
    @Transactional
    public String confirmTokens(String token) {

        ConfirmationToken confirmationToken =
                adminTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null ) {

            return "token/AlreadyConfirmedToken";
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {

            return "token/ExpiredToken";
        }

        // For Deployment
//        if(expiredAt.isBefore(LocalDateTime.now())) {
//
//            return "token/ExpiredToken";
//        }

        adminTokenService.setConfirmedAt(token);

        enableEmployee(confirmationToken.getEmployee().getEmailAddress());

        mailSender.sendApproveRegistration(confirmationToken.getEmployee().getEmailAddress(),
                emailTemplate.notificationConfirmed(confirmationToken.getEmployee().getFirstName(), confirmationToken.getEmployee().getLastName()));

        return "token/ConfirmedToken";
     }

    @Override
    public boolean emailAlreadyExist(String email) {
        return employeeRepository.findByEmailAddress(email).isPresent();
    }

    private int enableEmployee(String email) {
        return employeeRepository.enableAdminAccount(email);
    }

}
