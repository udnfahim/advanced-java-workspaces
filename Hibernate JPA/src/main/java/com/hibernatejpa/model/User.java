package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;
    private String username;
    private String password;

    @ManyToMany(cascade =  CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "userRoleMapping" , joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roleList;

    @Embedded
    private AuditInfo auditInfo;
}
