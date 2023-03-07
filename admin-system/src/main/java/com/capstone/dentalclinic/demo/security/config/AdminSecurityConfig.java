package com.capstone.dentalclinic.demo.security.config;

import com.capstone.dentalclinic.demo.services.administrator.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Order(2)
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

//            .authorizeHttpRequests((authorize) -> authorize
//                    .antMatchers("/").permitAll()
//                    .anyRequest().hasRole("ADMIN")
//            )
            http
                .csrf().disable()
                .authenticationProvider(daoAuthenticationProviderAdministrator())
                .antMatcher("/admin/**")
                .authorizeRequests().anyRequest().hasAnyAuthority("ADMIN")
                .and()
            .formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard")
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
