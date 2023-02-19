package com.capstone.dentalclinic.demo.security.config;

import com.capstone.dentalclinic.demo.model.patient.Patient;
import com.capstone.dentalclinic.demo.services.patient.PatientServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.capstone.dentalclinic.demo.services.administrator.EmployeeServiceImpl;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig {
    
    private final EmployeeServiceImpl employeeServiceImpl;
    private final PatientServicesImpl patientServicesImpl;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderAdministrator() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder);
        provider.setUserDetailsService(employeeServiceImpl);
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderPatient() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder);
        provider.setUserDetailsService(patientServicesImpl);
        return provider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainPatient (HttpSecurity http) throws  Exception{
        http
                .csrf().disable()
                .authenticationProvider(daoAuthenticationProviderPatient())
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/").permitAll()
                        .antMatchers("/patient/*").permitAll()
                        .antMatchers("/token/*").permitAll()
                        .antMatchers("/patient/dashboard").hasRole("PATIENT")
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/patient/login")
//                .usernameParameter()
                .defaultSuccessUrl("/patient/dashboard", true)
                .failureUrl("/admin/login-error");
        return http.build();
    }


    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainAdministrator (HttpSecurity http) throws  Exception{
        http
            .csrf().disable()
                .authenticationProvider(daoAuthenticationProviderAdministrator())
            .authorizeHttpRequests((authz) -> authz
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin/*").permitAll()
                    .antMatchers("/token/*").permitAll()
                    .antMatchers("/admin/dashboard").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin()
                .loginPage("/admin/login")
//                .usernameParameter()
                .defaultSuccessUrl("/admin/dashboard", true)
                .failureUrl("/admin/login-error");
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/resources/**","/static/**", "/static/*", "/static/", "/css/**", "/assets/**", "/javascript/**");
    }
}
