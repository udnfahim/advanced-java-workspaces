//package com.quiz4.config;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class CustomAuthProvider implements AuthenticationProvider {
//
//    private final Map<String, String> users = Map.of(
//            "admin", "123",
//            "user1", "abc"
//    );
//
//    private final Map<String, String> roles = Map.of(
//            "admin", "ADMIN",
//            "user1", "USER"
//    );
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        if (!users.containsKey(username) || !users.get(username).equals(password)) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        String role = roles.get(username);
//        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
//
//        return new UsernamePasswordAuthenticationToken(username, null, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
