package com.hibernatejpa.model;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String username ;
    private String description;

    @OneToOne(mappedBy = "studentProfile")
    private Student student;
}
