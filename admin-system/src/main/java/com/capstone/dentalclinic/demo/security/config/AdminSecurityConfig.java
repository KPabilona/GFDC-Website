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
import com.capstone.dentalclinic.demo.services.administrator.AdminServiceImpl;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
//@Order(2)
public class AdminSecurityConfig {
    
    private final AdminServiceImpl employeeServiceImpl;
    private final PasswordEncoder passwordEncoder;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderAdministrator() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bcryptPasswordEncoder());
        provider.setUserDetailsService(employeeServiceImpl);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChainAdministrator (HttpSecurity http) throws  Exception{
            http.authorizeRequests().antMatchers("/admin/login", "/admin/registration", "/admin/login-error",
                    "/forgot-password", "/admin/confirm", "/admin/confirm").permitAll();
//            , "/admin/dashboard",
//                "/admin/patients-list", "/admin/cancelled", "/admin/delete-patient", "/admin/patient",
//                "/admin/save", "/admin/new-patient", "/admin/delete", "/admin/cancel-appointment", "/admin/delete" +
//                "-appointment", "/admin/add-appointment", "/admin/schedule-patient"
            http
                .csrf().disable()
                .authenticationProvider(daoAuthenticationProviderAdministrator())
                .antMatcher("/admin/**")
                .authorizeRequests().anyRequest().hasAnyAuthority("ADMIN")
                .and()
            .formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .failureUrl("/admin/login-error");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/resources/**","/static/**", "/static/*", "/static/", "/css/**", "/assets/**",
                        "/javascript/**");
    }
}
