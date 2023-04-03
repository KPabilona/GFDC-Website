package com.capstone.dentalclinic.demo.controller.patient;

import java.security.Principal;

import com.capstone.dentalclinic.demo.model.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.services.appointment.AppointmentServices;
import com.capstone.dentalclinic.demo.services.patient.PatientService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {
    
    private final PatientService patientService;

    private final AppointmentServices appointmentServices;

    @GetMapping("/dashboard")
    public ModelAndView patientDashboard(Principal principal) {
        ModelAndView mav = new ModelAndView("/PatientWebPages/PatientDashboard");
        Patient patient = patientService.findByEmailAddress(principal.getName());
        mav.addObject("appointment", new Appointment());
        mav.addObject("times", Time.values());
        mav.addObject("services", Services.values());
        mav.addObject("data", patient);

        return mav;
    }


    @PostMapping("/dashboard") 
    public ModelAndView patientAppointmentSchedule(@ModelAttribute("appointment") AppointmentDTO appointmentDTO, Principal principal) {
        ModelAndView mav = new ModelAndView("/PatientWebPages/PatientDashboard");
        mav.addObject("appointment", new AppointmentDTO());
        mav.addObject("times", Time.values());
        mav.addObject("services", Services.values());
        appointmentServices.saveAppointment(appointmentDTO, principal);
        return mav;
    }
}
