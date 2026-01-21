package com.quiz4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {
    @Id
    private String id;
    private String customerName;
    private String productName;
    private Integer quantity;
    private double totalPrice;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private OrderStatus orderStatus;
}
