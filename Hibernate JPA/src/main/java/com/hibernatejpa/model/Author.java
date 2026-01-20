package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String authorName ;
    private String authorNumber;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "authorBookMapping" , joinColumns = @JoinColumn(name ="authorId"))
    @Column(name = "bookId")
    private List<Book> bookList;
    // join column , book can't be na single column that holds all authors data same as authors , so need a join table

}
