package com.capstone.dentalclinic.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
