package com.hibernatejpa.model;

import jakarta.persistence.*;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private double price;

    @ManyToOne
    private Booking booking;
}
