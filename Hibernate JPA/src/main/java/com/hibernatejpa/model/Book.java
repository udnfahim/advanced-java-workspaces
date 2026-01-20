package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String bookName;
    private String genera;

    @ManyToMany(mappedBy = "bookList")
    private List<Author> authorList;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city" , column = @Column(name = "publisherCity")),
            @AttributeOverride(name = "street" , column = @Column(name = "publisherStreet")),
            @AttributeOverride(name = "country" , column = @Column(name = "publisherCountry")),
    }
    )
    private Address publisherAddress;


    //there are
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinTable(name = "bookISBNResisterMapping")
    private ISBNRegistration isbnRegistration;
}
