package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prodects")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String productName;
    private String productCode;

    @OneToMany(mappedBy = "product" )
    private List<OrderItem> orderItemList;

    @ElementCollection
    @CollectionTable(name = "ElementCollection" , joinColumns = @JoinColumn(name = "productId"))
    private Set<String> elementCollection;
}
