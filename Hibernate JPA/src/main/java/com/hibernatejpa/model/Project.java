package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String projectName;
    private String projectDetails;

    @ManyToMany(mappedBy = "projectList")
    private List<Employee> employeeList;
}
