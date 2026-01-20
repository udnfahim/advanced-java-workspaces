package com.hibernatejpa.model;

import jakarta.persistence.*;

@Entity
public class ISBNRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String ISBN;
    @OneToOne(mappedBy = "isbnRegistration")
    private Book book;
}
