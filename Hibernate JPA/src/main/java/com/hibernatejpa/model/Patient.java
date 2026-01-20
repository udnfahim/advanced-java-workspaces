package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    private String number;

    @Embedded
    private MedicalHistory medicalHistory;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "patientAppointmentMapping", joinColumns = @JoinColumn(name = "PatientId") , inverseJoinColumns = @JoinColumn(name = "appointmentId"))
    private List<Appointments> appointmentsList;
}
