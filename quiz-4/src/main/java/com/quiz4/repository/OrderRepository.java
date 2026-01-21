package com.quiz4.repository;

import com.quiz4.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    //using ai

    @Query("{ 'localDateTime': { $gte: ?0, $lt: ?1 } }")
    List<Order> findOrdersForToday(LocalDateTime todayStart, LocalDateTime tomorrowStart);

    @Query("{ 'localDateTime': { $gte: ?0 } }")
    List<Order> findOrdersForThisMonth(LocalDateTime monthStart);
}
