package com.quiz4.repository;

import com.quiz4.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByOrderTimeAfter(LocalDateTime startOfMonth);
    List<Order> findByOrderStatus(String status);
}
