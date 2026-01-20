package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name ;
    private String qr;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bookingPPackageMappping", joinColumns = @JoinColumn(name = "bookingId"), inverseJoinColumns = @JoinColumn(name = "packageId"))
    private List<Package> packageList;

    @ManyToOne()
    private Customer customer;
}
