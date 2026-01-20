package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
    private String roleDetails;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    @ManyToMany(mappedBy = "roles")
    private List<Permission> permissions;
}
