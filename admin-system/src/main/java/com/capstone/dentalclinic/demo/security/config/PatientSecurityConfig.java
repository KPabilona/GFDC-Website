package com.capstone.dentalclinic.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.capstone.dentalclinic.demo.security.PasswordEncoder;
import com.capstone.dentalclinic.demo.services.patient.PatientServicesImpl;

import lombok.AllArgsConstructor;

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
            http.authorizeRequests().antMatchers("/patient/login",
                    "/patient/registration", "/Service", "/patient/login-error", "/patient/login-success", "/forgot-password",
                    "/forgot-password-success", "/new-password", "/logout", "/patient/confirm", "/reviews").permitAll();

            http
                .csrf().disable()
                .authenticationProvider(daoAuthenticationProviderPatient())
                .antMatcher("/patient/**")
                .authorizeRequests().anyRequest().hasAnyAuthority("PATIENT")
                .and()
            .formLogin()
                .loginPage("/patient/login")
                .defaultSuccessUrl("/patient/dashboard", true)
                .failureUrl("/patient/login-error");
//            .and()
//            .logout(logout -> logout
//                    .logoutUrl("/logout")
//                    .invalidateHttpSession(true)
//                    .logoutSuccessUrl("/patient/login"));
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .logoutSuccessUrl("/patient/login")
//                            .logoutSuccessHandler(logoutSuccessHandler)
//                            .addLogoutHandler(logoutHandler)
//                .deleteCookies("JSESSIONID");
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
