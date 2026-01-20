package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private String degree;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinTable(name = "doctorAppointmentsMapping", joinColumns = @JoinColumn(name = "doctorId"), inverseJoinColumns = @JoinColumn(name = "appointmentsId"))
    private List<Appointments> appointmentsList;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    @JoinTable(name = "doctorAppointmentMapping" , joinColumns = @JoinColumn(name = "doctorId") , inverseJoinColumns = @JoinColumn(name = "appointmnetId"))
    private List<Appointments> appointments;
}
