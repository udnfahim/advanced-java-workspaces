package com.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(request -> request
                .requestMatchers("/login","/registration","/contact-us").permitAll()
                .requestMatchers("/dashboard","/profile").authenticated()
                .requestMatchers("/settings","forgotten-password").fullyAuthenticated()

        )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .rememberMe(Customizer.withDefaults())

        ;

        return httpSecurity.build();
    }
}
