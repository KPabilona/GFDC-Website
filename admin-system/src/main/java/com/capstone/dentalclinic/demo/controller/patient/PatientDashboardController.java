package com.capstone.dentalclinic.demo.controller.patient;

import com.capstone.dentalclinic.demo.DTO.AppointmentDTO;
import com.capstone.dentalclinic.demo.model.Services;
import com.capstone.dentalclinic.demo.model.Time;
import com.capstone.dentalclinic.demo.model.appointment.Appointment;
import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.repository.appointment.AppointmentRepository;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientDashboardController {
    
    private final PatientService patientService;

    private final AppointmentRepository appointmentRepository;
    private final AppointmentServices appointmentServices;

    @GetMapping("/dashboard")
    public ModelAndView patientDashboard(@ModelAttribute("appointment") @Valid AppointmentDTO appointment,
                                         BindingResult bindingResult, Principal principal) {

        ModelAndView mav = new ModelAndView("PatientWebPages/PatientDashboard");

//        Patient patient = patientService.findByEmailAddress(principal.getName());

        bindingResult.hasErrors();
        mav.addObject("times", Time.values());
        mav.addObject("services", Services.values());
//        mav.addObject("data", patient);
//        mav.addObject("appointmentSchedule", appointmentServices.getAppointmentSchedule(patient.getId()));
        mav.addObject("appointment", new Appointment());
        return mav;
    }

    @PostMapping("/dashboard")
    public String submitAppointment(@ModelAttribute("appointment") @Valid AppointmentDTO appointment,
                                    BindingResult bindingResult, Model model, Principal principal) {

        Patient patient = patientService.findByEmailAddress(principal.getName());

        List<Appointment> appointmentData = appointmentServices.getAppointmentSchedule(patient.getId());
        List<Appointment> allAppointment = appointmentRepository.getAllAppointment();

        for (Appointment app :
                appointmentData) {
                if( app.getPickDate().equals(appointment.getPickDate()) &&
                    app.getPickTime().equalsIgnoreCase(appointment.getPickTime().getDisplayTime())) {

                    model.addAttribute("data", patient);
                    model.addAttribute("times", Time.values());
                    model.addAttribute("services", Services.values());
                    model.addAttribute("appointmentSchedule", appointmentData);

                    model.addAttribute("isTaken", true);
                    return "PatientWebPages/PatientDashboard";
                }
        }

        for(Appointment appointment1 : allAppointment) {
            if(appointment.getPickDate().equals(appointment1.getPickDate()) &&
                    appointment1.getPickTime().equalsIgnoreCase(appointment.getPickTime().getDisplayTime())) {

                model.addAttribute("data", patient);
                model.addAttribute("times", Time.values());
                model.addAttribute("services", Services.values());
                model.addAttribute("appointmentSchedule", appointmentData);

                model.addAttribute("isTaken", true);
                return "PatientWebPages/PatientDashboard";
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("data", patient);
            model.addAttribute("times", Time.values());
            model.addAttribute("services", Services.values());
            model.addAttribute("appointmentSchedule", appointmentData);
            model.addAttribute("isTaken", true);
            return "PatientWebPages/PatientDashboard";
        }else {
            model.addAttribute("data", patient);
            model.addAttribute("times", Time.values());
            model.addAttribute("services", Services.values());

            appointmentServices.saveAppointment(appointment, principal);
            return "redirect:/patient/dashboard#record";
        }
    }
}
