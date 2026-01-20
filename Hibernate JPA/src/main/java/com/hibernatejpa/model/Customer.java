package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city" , column = @Column(name = "costomerCity")),
            @AttributeOverride(name = "street" , column = @Column(name = "costomerStreet")),
            @AttributeOverride(name = "country" , column = @Column(name = "costomerCountry"))
    })
    private Address customerAddress;

    @OneToMany(cascade = CascadeType.PERSIST , orphanRemoval = true , fetch = FetchType.LAZY)
    @JoinTable(name = "costomerOrderMapping" , joinColumns = @JoinColumn(name = "costomerId"), inverseJoinColumns = @JoinColumn(name = "oderId"))
    private List<Order> orderList;

    @Embedded
    private TravelDocument travelDocument;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "customerBookingMapping" , joinColumns = @JoinColumn(name = "customerId"), inverseJoinColumns = @JoinColumn(name = "bookingId"))
    private List<Booking> bookingList;
}
