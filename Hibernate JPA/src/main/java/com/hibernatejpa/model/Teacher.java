package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private String number;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courseList; // here need a list cause one to many mapping with reference key
    // mappedBy is required because when we use ManyToOne and ManyToMany that's time we need to define the owner and where has primary key like here course is owner,

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinTable(name = "teacherSubjectmapping" , joinColumns = @JoinColumn(name = "teacherId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private List<Subject> subjects;
}
