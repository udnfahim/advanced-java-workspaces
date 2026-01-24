package com.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {
    @Id
    private String id;

    private String fullName;
    private String username;
    private String email;
    private String mobile;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private boolean enabled = false;
    private boolean active = true;

    private boolean rememberMe;

    private Instant createdAt = Instant.now();
}
