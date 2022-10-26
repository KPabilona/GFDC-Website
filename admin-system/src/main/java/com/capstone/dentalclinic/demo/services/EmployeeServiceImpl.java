package com.capstone.dentalclinic.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.capstone.dentalclinic.demo.DTO.EmployeeDTO;
import com.capstone.dentalclinic.demo.mail.MailSender;
import com.capstone.dentalclinic.demo.model.EmployeeRole;
import com.capstone.dentalclinic.demo.model.token.ConfirmationToken;
import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.dentalclinic.demo.model.Employee;
import com.capstone.dentalclinic.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements UserDetailsService,EmployeeService{
    private final static String USER_NOT_FOUND_MSG =
    "User email %s not found";

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Employee employee = employeeRepository.findByEmailAddress(email);

        if(employee == null) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }

        UserDetails userDetails =
                User.withUsername(employee.getUsername()).password(employee.getPassword()).authorities("ADMIN").build();

         return userDetails;
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

    // This will Create data for the EmployeeModel
    public void registerNewEmployee(EmployeeDTO employeeDTO) {

        if(emailExist(employeeDTO.getEmailAddress()) ){
            throw new RuntimeException("Email already exist!");
        }

        String encode = passwordEncoder.bcryptPasswordEncoder().encode(employeeDTO.getEmployeePassword());

        Employee employee1 = new Employee();
        employee1.setFirstName(employeeDTO.getFirstName());
        employee1.setMiddleName(employeeDTO.getMiddleName());
        employee1.setLastName(employeeDTO.getLastName());
        employee1.setEmailAddress(employeeDTO.getEmailAddress());
        employee1.setEmployeePassword(encode);
        employee1.setAddress(employeeDTO.getAddress());
        employee1.setGender(employeeDTO.getGender());
        employee1.setContactNumber(employeeDTO.getContactNumber());
        employee1.setEmployeeRole(EmployeeRole.ADMIN);
        employee1.setBirthDate(employeeDTO.getBirthDate());
        employee1.setEnable(false);
        employee1.setLocked(false);
        employeeRepository.save(employee1);


        System.out.println("The Username for spring security is " + employee1.getUsername());
        System.out.println("The Username for spring security is " + employee1.getPassword());

        // Creating a Token before saving the employee
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), employee1);

        final String link = "http:localhost:8080/system/admin/confirm?token=" + token;

        mailSender.sendConfirmationMail(employee1.getEmailAddress(),
                mailBuilder(employee1.getFirstName(), employee1.getLastName(),
                employee1.getEmailAddress(), employee1.getAddress(), employee1.getContactNumber(), link));

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        System.out.println("The Confirmation Token is " + confirmationToken.getToken());
    }

    @Override
    @Transactional
    public String confirmTokens(String token) {
        ConfirmationToken confirmationToken =
                confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null ) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("email already confirmed");
        }

        confirmationTokenService.setConfirmedAt(token);

        enableEmployee(confirmationToken.getEmployee().getEmailAddress());

        return "/dashboard/Dashboard";
     }

    private int enableEmployee(String email) {
        return employeeRepository.enableAppUser(email);
    }

    private boolean emailExist(String email) {
        return employeeRepository.findByEmailAddress(email) != null;
    }

    private String mailBuilder(String firstName,
                               String lastName,
                               String emailAddress,
                               String address,
                               String contactNumber,
                               String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi Admin " + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Someone Successfully Registered as an Admin, and you can verify it's credentials, the credentials are showed bellow: </p>" + "</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> - FirstName: " + firstName + " </p>" +
                "</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> - LastName: "+ lastName + " " +
                "</p>" + "</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> - Email " +
                "Address: "+ emailAddress + " </p>" + "</p><p style=\"Margin:0 0 20px 0;font-size:19px;" +
                "line-height:25px;color:#0b0c0c\"> - ContactNumber: "+ contactNumber + " </p>" + "</p><p " +
                "style=\"Margin" +
                ":0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> - Home Address: " + address + " </p>" +
                "<blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;" +
                "font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;" +
                "color:#0b0c0c\"> <a href=\"" + link + "\" target=\"_blank\">Activate Now</a> </p></blockquote>\n " +
                "Link will " +
                "expire in " +
                "30 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
