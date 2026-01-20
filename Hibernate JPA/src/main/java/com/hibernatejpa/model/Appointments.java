package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    private LocalDate date;

    @ManyToOne()
    private Doctor doctor;

    @ManyToMany(mappedBy = "appointmentsList")
    private List<Patient> patientList;

    @ManyToMany(mappedBy = "appointments")
    private List<Doctor> doctorList;
}
