//package com.quiz4.config;
//
//import com.quiz4.jwtFilter.JwtAuthenticationFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtFilter;
//    private final CustomAuthProvider customAuthProvider;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtFilter, CustomAuthProvider customAuthProvider) {
//        this.jwtFilter = jwtFilter;
//        this.customAuthProvider = customAuthProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/auth/login", "/css/**", "/js/**").permitAll()
//                        .requestMatchers("/index", "/order").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .authenticationProvider(customAuthProvider)
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}
