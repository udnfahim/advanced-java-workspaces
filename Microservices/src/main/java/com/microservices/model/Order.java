package com.microservices.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private Long customerId;
    private List<Item> items;
    private Double totalAmount;
    private LocalDateTime orderDate = LocalDateTime.now();
    private String status = "PLACED";
}
