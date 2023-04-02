package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.services.administrator.AdminTokenService;
import com.capstone.dentalclinic.demo.services.administrator.AdminService;
import com.capstone.dentalclinic.demo.services.patient.PatientTokenService;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class Token {

    private final AdminService adminService;

    private final AdminTokenService adminTokenService;

    // This will handle the confirmation of the patient.
    @GetMapping("/confirm")
    public String adminConfirmToken(@RequestParam("tokens") String token, Model model) {
        System.out.println("PARAMETER"+ token);
        String result = adminService.confirmTokens(token);
        System.out.println("THE RESULT IS TOKE: " + result);
        if(result.equalsIgnoreCase("token/AlreadyConfirmedToken")) {
            model.addAttribute("errorMessage", adminTokenService.getConfirmedAt(token));
            return result;
        }
        return result;
    }

    @GetMapping("/Confirmed")
    public String viewConfirmedTokenPage() {
        return "token/ConfirmedToken";
    }

    @GetMapping("/Expired")
    public String viewExpiredTokenPage () {
        return "token/ExpiredToken";
    }

    @GetMapping("/Done")
    public String viewAlreadyConfirmedTokenPage() {
        return "token/AlreadyConfirmedToken";
    }
}
