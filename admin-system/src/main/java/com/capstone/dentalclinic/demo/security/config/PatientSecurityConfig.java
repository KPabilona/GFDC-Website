package com.capstone.dentalclinic.demo.security.config;

import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import com.capstone.dentalclinic.demo.services.patient.PatientServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Order(1)
public class PatientSecurityConfig {

    private final PatientServicesImpl patientServicesImpl;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderPatient() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bcryptPasswordEncoder());
        provider.setUserDetailsService(patientServicesImpl);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChainPatient (HttpSecurity http) throws  Exception{
            http.authorizeRequests().antMatchers("/patient/login", "/patient/registration").permitAll();

//        http
//            .csrf().disable()
//            .authenticationProvider(daoAuthenticationProviderPatient())
//
//            .authorizeHttpRequests((authorize) -> authorize
//                    .antMatchers("/").permitAll()
//                    .antMatchers("/patient/login").permitAll()
//                    .anyRequest().hasRole("PATIENT")
//            )
            http
                .csrf().disable()
                .authenticationProvider(daoAuthenticationProviderPatient())
                .antMatcher("/patient/**")
                .authorizeRequests().anyRequest().hasAnyAuthority("PATIENT")
                .and()
            .formLogin()
                .loginPage("/patient/login")
                .defaultSuccessUrl("/patient/dashboard", true)
                .failureUrl("/patient/login");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer1() {
        return (web) -> web
                .ignoring()
                .antMatchers("/resources/**","/static/**", "/static/*", "/static/", "/css/**", "/assets/**",
                        "/javascript/**");
    }
}
