package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    private int number;

    @OneToMany(mappedBy = "classroom")
    private List<Student> studentList;
}
