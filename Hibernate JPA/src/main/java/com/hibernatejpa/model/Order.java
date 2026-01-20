package com.hibernatejpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String orderName;
    private String orderId;

    @OneToMany(orphanRemoval = true , mappedBy = "order")
    private List<OrderItem> orderItemList;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city" , column = @Column(name = "shippingCity")),
            @AttributeOverride(name = "street" , column = @Column(name = "shippingStreet")),
            @AttributeOverride(name = "country" , column = @Column(name = "shippingCountry")),
    })
    private Address shippingAddress;

    @ManyToOne()
    private Customer customer;
}
