package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.DTO.AdminDashboardDateTimeDTO;
import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.DTO.CancelAppointment;
import com.capstone.dentalclinic.demo.DTO.PatientDTO;
import com.capstone.dentalclinic.demo.model.Gender;
import com.capstone.dentalclinic.demo.model.MaritalStatus;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.repository.patient.PatientRepository;
import com.capstone.dentalclinic.demo.services.appointment.AppointmentServices;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class DashboardController {

    private final AppointmentServices appointmentServices;
    private final PatientService patientService;

    private final PatientRepository patientRepository;

    @GetMapping("/dashboard")
    public String getDashboard( Model model) {
        model.addAttribute("appointment", new AdminDashboardDateTimeDTO());
        model.addAttribute("countPatient", patientService.countAllPatients());
        model.addAttribute("countAppointment2", appointmentServices.countAppointmentToday2());
        model.addAttribute("listOfAppointment", appointmentServices.listOfAppointment(LocalDate.now()));
        model.addAttribute("cancelAppointment", new CancelAppointment());
        return "/dashboard/Dashboard";
    }

    @PostMapping("/delete")
    public String cancelAppointment(@ModelAttribute("cancelAppointment") CancelAppointment cancelAppointment,
                                    Model model) {
        System.out.println("MESSAGE " + cancelAppointment.getMessage());

        return "redirect:/admin/dashboard";
    }

    @GetMapping("delete-appointment")
    public String deleteAppointment(@RequestParam Long id) {
        System.out.println("COMMITTED!");
        appointmentServices.cancelAppointment(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/dashboard")
    public String listOfPatientAppointment(@ModelAttribute("appointment") @Valid AdminDashboardDateTimeDTO appointment,
                                           BindingResult bindingResult, Model model) {

        if(bindingResult.hasFieldErrors("pickDate")) {
            model.addAttribute("countPatient", patientService.countAllPatients());
            return "/dashboard/Dashboard";
        }
        model.addAttribute("countPatient", patientService.countAllPatients());
        model.addAttribute("listOfAppointment", appointmentServices.listOfAppointment(appointment.getPickDate()));
        return "/dashboard/Dashboard";
    }

    @GetMapping("/patients-list")
    public String getPatientList(Model model) {
        List<Patient> patientList = patientService.findAllPatient();
        model.addAttribute("listPatient", patientList);
        return "/dashboard/clients";
    }

    @GetMapping("/patient")
    public String getPatientById(@RequestParam Long id, Model model) {

        Patient patient = patientRepository.findById(id).get();
        System.out.println("Patient Controller " + patient);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());
        model.addAttribute("patient", patient);
        return "dashboard/UpdatePatient";
    }

    @GetMapping("/new-patient")
    public String getNewPatientForm(Model model) {
        model.addAttribute("patient", new PatientDTO());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());
        return "dashboard/newpatient";
    }

    @PostMapping("/new-patient")
    public String addNewPatient(@ModelAttribute("patient") @Valid PatientDTO patientDTO,
                                BindingResult bindingResult,
                                Model model) {

        final long contact = String.valueOf(patientDTO.getContactNumber()).length();

        if(bindingResult.hasErrors() || bindingResult.hasFieldErrors("emailAddress")
                || !patientService.isMatchedPassword(patientDTO)
                || patientService.patientEmailAlreadyExist(patientDTO.getEmailAddress())
                || contact > 10
                || contact < 10) {

            if(patientService.patientEmailAlreadyExist(patientDTO.getEmailAddress())){
                model.addAttribute("isEmailExists", "Email Already Exists, Try Another One.");
                model.addAttribute("genders", Gender.values());
                model.addAttribute("maritalStatus", MaritalStatus.values());
                return "dashboard/newpatient";
            }else if(!patientService.isMatchedPassword(patientDTO)) {
                model.addAttribute("isMatchedPassword", !patientService.isMatchedPassword(patientDTO));
                model.addAttribute("genders", Gender.values());
                model.addAttribute("maritalStatus", MaritalStatus.values());
                model.addAttribute("isMatchedPassword", true);
                return "dashboard/newpatient";
            } else if (contact > 10 || contact < 10) {
                model.addAttribute("genders", Gender.values());
                model.addAttribute("maritalStatus", MaritalStatus.values());
                model.addAttribute("contactNumberError", true);
            }
            model.addAttribute("genders", Gender.values());
            model.addAttribute("maritalStatus", MaritalStatus.values());
            return "dashboard/newpatient";
        }
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());

        patientService.registerNewPatient(patientDTO);

        return "redirect:/admin/patient-list";
    }


    @PostMapping("/save")
    public String saveUpdatePatient(@ModelAttribute("patient") Patient patient) {
        System.out.println("THE PATIENT POST " + patient);
        patientService.saveUpdate(patient);
        return "redirect:/admin/patients-list";
    }

    @GetMapping("/delete-patient")
    public String deletePatient(@RequestParam Long id) {
        patientService.deleteById(id);
        return "redirect:/admin/patients-list";
    }
}
