package com.capstone.dentalclinic.demo.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class WebPages {
    
    @GetMapping("/")
    public String landingPage() {
        return "PatientWebPages/index";
    }


}
