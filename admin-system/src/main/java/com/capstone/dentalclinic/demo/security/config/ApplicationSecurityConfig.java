package com.capstone.dentalclinic.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.capstone.dentalclinic.demo.model.EmployeeRole;
import com.capstone.dentalclinic.demo.services.EmployeeServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig {
    
    private final EmployeeServiceImpl employeeServiceImpl;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder);
        provider.setUserDetailsService(employeeServiceImpl);
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws  Exception{
        http
            .csrf().disable()
                .authenticationProvider(daoAuthenticationProvider())
            .authorizeHttpRequests((authz) -> authz

                    .antMatchers("/").permitAll()
                    .antMatchers("/system/**").permitAll()
                    .antMatchers("/token/*").permitAll()
                    .anyRequest().authenticated()
                    .antMatchers("/*").permitAll()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/system/**").permitAll()
                    .antMatchers("/token/*").permitAll()
                    .antMatchers("/admin/dashboard/").authenticated()
            )
            .formLogin()
                .loginPage("/system/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .failureUrl("/system/admin/login-error")

                .permitAll()
                .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

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
