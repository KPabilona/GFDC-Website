package com.capstone.dentalclinic.demo.exceptionhandler.advice;

import com.capstone.dentalclinic.demo.exceptionhandler.EmailAlreadyTakenException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(EmailAlreadyTakenException.class)
//    public String handleTakenEmail(EmailAlreadyTakenException emailAlreadyTakenException) {
//
//        return "admin/Registration";
//    }
}
