package com.capstone.dentalclinic.demo.controller.administrator;

import com.capstone.dentalclinic.demo.services.administrator.ConfirmationTokenService;
import com.capstone.dentalclinic.demo.services.administrator.EmployeeService;
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

    private final EmployeeService employeeService;

    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token, Model model) {

        String result = employeeService.confirmTokens(token);

        if(result.equalsIgnoreCase("token/AlreadyConfirmedToken")) {
            model.addAttribute("errorMessage", confirmationTokenService.getConfirmedAt(token));
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
