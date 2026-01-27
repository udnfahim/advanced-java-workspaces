package com.quiz4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class User {
    @Id
    private String username;
    private String password;
    private Role role;
}