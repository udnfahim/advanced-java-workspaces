package com.hibernatejpa.model;

import jakarta.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private String code ;

    @ManyToOne()
    private Teacher teacher;
}
