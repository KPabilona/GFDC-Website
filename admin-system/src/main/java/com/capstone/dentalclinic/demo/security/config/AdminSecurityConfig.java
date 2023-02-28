package com.capstone.dentalclinic.demo.security.config;

import com.capstone.dentalclinic.demo.services.administrator.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AdminSecurityConfig {
    
    private final AdminServiceImpl employeeServiceImpl;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderAdministrator() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder);
        provider.setUserDetailsService(employeeServiceImpl);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChainAdministrator (HttpSecurity http) throws  Exception{
        http
            .csrf().disable()
            .authenticationProvider(daoAuthenticationProviderAdministrator())
            .authorizeHttpRequests((authz) -> authz
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin/dashboard").hasRole("ADMIN")
            )
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
                        "/javascript/**", "/admin/**", "/patients/**");
    }
}
