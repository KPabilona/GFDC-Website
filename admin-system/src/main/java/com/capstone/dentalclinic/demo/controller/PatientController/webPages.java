package com.capstone.dentalclinic.demo.controller.PatientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class webPages {
    
    @GetMapping("/")
    public String landingpage() {
        return "PatientWebPages/index";
    }


}
