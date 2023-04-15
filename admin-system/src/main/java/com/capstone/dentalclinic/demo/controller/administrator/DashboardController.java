package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.DTO.AdminDashboardDateTimeDTO;
import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
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
        model.addAttribute("listOfAppointment", appointmentServices.listOfAppointment(LocalDate.now()));
        return "/dashboard/Dashboard";
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
        model.addAttribute("genders", Gender.values());
        model.addAttribute("maritalStatus", MaritalStatus.values());
        model.addAttribute("patient", patient);
        return "dashboard/UpdatePatient";
    }

    @PostMapping("/save")
    public String saveUpdatePatient(@ModelAttribute("patient") Patient patient) {
//        patientService.saveUpdate(patient);
        patientRepository.save(patient);
        return "redirect:/admin/patients-list";
    }

    @GetMapping("/delete-patient")
    public String deletePatient(@RequestParam Long id) {
        patientService.deleteById(id);
        return "redirect:/admin/patients-list";
    }
}
