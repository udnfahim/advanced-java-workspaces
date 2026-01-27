package com.quiz4.service;

import com.quiz4.model.Order;
import com.quiz4.model.OrderStatus;
import com.quiz4.repository.OrderRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @CacheEvict(value = {"orders", "todayOrders", "monthlyRevenue"}, allEntries = true)
    public Order createOrder(Order order) {

        validateOrder(order);

        if(order.getOrderStatus() == null){
            order.setOrderStatus(OrderStatus.PENDING);
        }

        order.setOrderTime(LocalDateTime.now());

        if(order.getTotalPrice() != null && order.getQuantity() != null){
            order.setTotalPrice(order.getTotalPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        }

        return orderRepository.save(order);
    }

    @Cacheable(value = "orders")
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    @Cacheable(value = "orders", key = "#id")
    public Order getOrderById(String id) {

        if(!StringUtils.hasText(id)){
            throw new IllegalArgumentException("Order ID must not be empty");
        }
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

    }

    @CacheEvict(value = {"orders", "todayOrders", "monthlyRevenue"}, allEntries = true)
    public Order updateOrder(String id, Order order) {

        validateOrder(order);

        Order existing = getOrderById(id);

        existing.setCustomerName(order.getCustomerName());
        existing.setProductName(order.getProductName());
        existing.setQuantity(order.getQuantity());
        existing.setOrderStatus(order.getOrderStatus());

        if(order.getTotalPrice() != null && order.getQuantity() != null){

            existing.setTotalPrice(order.getTotalPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        }

        return orderRepository.save(existing);
    }

    @CacheEvict(value = {"orders", "todayOrders", "monthlyRevenue"}, allEntries = true)
    public Order updateOrderStatus(String id, OrderStatus status){

        if(status == null){
            throw new IllegalArgumentException("Order status must not be null");
        }

        Order order = getOrderById(id);
        order.setOrderStatus(status);

        return orderRepository.save(order);
    }

    @CacheEvict(value = {"orders", "todayOrders", "monthlyRevenue"}, allEntries = true)
    public void deleteOrder(String id){
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    @Cacheable(value = "todayOrders")
    public int getTodayOrders(){

        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime tomorrowStart = todayStart.plusDays(1);

        List<Order> todayOrders = orderRepository.findByOrderTimeBetween(todayStart, tomorrowStart);
        return todayOrders.size();
    }

    @Cacheable(value = "monthlyRevenue")
    public BigDecimal getMonthlyRevenue(){

        LocalDateTime monthStart = YearMonth.now().atDay(1).atStartOfDay();

        List<Order> monthOrders = orderRepository.findByOrderTimeAfter(monthStart);
        return monthOrders.stream()
                .filter(Objects::nonNull)
                .map(Order::getTotalPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private void validateOrder(Order order){

        if(order == null){
            throw new IllegalArgumentException("Order must not be null");
        }

        if(!StringUtils.hasText(order.getCustomerName())){
            throw new IllegalArgumentException("Customer name must not be empty");
        }

        if(!StringUtils.hasText(order.getProductName())){
            throw new IllegalArgumentException("Product name must not be empty");
        }

        if(order.getQuantity() == null || order.getQuantity() <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if(order.getTotalPrice() == null || order.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Total price must be greater than 0");
        }
    }
}
