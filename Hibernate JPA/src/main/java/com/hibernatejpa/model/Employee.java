package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String employeeName;
    private String employeeId;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER )
    @JoinTable(name = "departmentEmploeeMapping")
    private Department department;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city" , column = @Column(name = "employeeCity")),
            @AttributeOverride(name = "street" , column = @Column(name = "employeeStreet")),
            @AttributeOverride(name = "country" , column = @Column(name = "employeeCountry")),
    })
    private Address address;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_projects_table")
    private List<Project> projectList;
}
