package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.DTO.AdminDashboardDateTimeDTO;
import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.services.appointment.AppointmentServices;
import com.capstone.dentalclinic.demo.services.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class DashboardController {

    private final AppointmentServices appointmentServices;
    private final PatientService patientService;
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

}
