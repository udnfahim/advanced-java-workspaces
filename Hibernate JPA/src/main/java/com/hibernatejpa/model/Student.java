package com.hibernatejpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students") // set the table name
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    private double cgpa ;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "student_course_mapping" , joinColumns = @JoinColumn(name = "studentId"))
    private List<Course> courseList ;

    @ElementCollection
    @CollectionTable(name = "studentEmail" , joinColumns = @JoinColumn(name = "studentId"))
    @Column(name = "email")
    private Set<String> stringSet;

    // we use elementCollection instead of entity cause of ,to create a list of email no need separated id so that it can a an independent table thats why we use elementCollection to create an relationship ;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.LAZY)
    @JoinTable(name = "studentProfile")
    private Profile studentProfile;
    // orphanRemoval= true means if we delete parent child will be default or auto deleted, here student parent if we delete this profile will be deleted.


    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "studentCLassroomMapping" , joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "classroomId"))
    private Classroom classroom;
}
