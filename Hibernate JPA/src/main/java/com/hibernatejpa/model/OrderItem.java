package com.hibernatejpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String itemName;
    private Double itemPrice;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "oderOderItemMapping")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "productOrderitemMapping")
    private Product product;
}
