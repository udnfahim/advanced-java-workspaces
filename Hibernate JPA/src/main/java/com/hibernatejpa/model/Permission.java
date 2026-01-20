package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String permissionType;
    private String permissionDetails;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "permissionRoleMapping" , joinColumns = @JoinColumn(name = "permissionId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

}
