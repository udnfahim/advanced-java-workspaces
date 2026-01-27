package com.springsecurity.service;

import com.springsecurity.model.Role;
import com.springsecurity.model.User;
import com.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String login, String rawPassword) {

        Optional<User> optionalUser = userRepository.findUserByUsername(login);

        if (optionalUser.isEmpty()) optionalUser = userRepository.findByEmail(login);
        if (optionalUser.isEmpty()) optionalUser = userRepository.findByMobile(login);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified");
        }

        return user;
    }

    public void register(String fullName, String username, String email, String mobile, String password, Role role) {

        if (userRepository.existsByUsername(username)) throw new RuntimeException("Username already taken");
        if (userRepository.existsByEmail(email)) throw new RuntimeException("Email already registered");
        if (userRepository.existsByMobile(mobile)) throw new RuntimeException("Mobile already registered");

        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
    }
}
