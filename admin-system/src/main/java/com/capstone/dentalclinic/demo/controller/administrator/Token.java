package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.services.administrator.AdminTokenService;
import com.capstone.dentalclinic.demo.services.administrator.AdminService;
import com.capstone.dentalclinic.demo.services.patient.PatientTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/token")
public class Token {

    private final AdminService adminService;

    private final AdminTokenService adminTokenService;
    private final PatientTokenService patientTokenService;

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token, Model model) {

        String result = adminService.confirmTokens(token);
        String patientResult = patientTokenService.patientConfirmationToken(token);

        if(result.equalsIgnoreCase("token/AlreadyConfirmedToken")) {
            model.addAttribute("errorMessage", adminTokenService.getConfirmedAt(token));
            return result;
        }else if(patientResult.equalsIgnoreCase("token/AlreadyConfirmedToken")) {
            model.addAttribute("errorMessage", patientTokenService.getConfirmedAt(token));
            return patientResult;
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
