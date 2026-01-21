package com.quiz4.service;

import com.quiz4.model.Order;
import com.quiz4.model.OrderStatus;
import com.quiz4.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // CRUD operations
    public List<Order> getAllOrders(){ return orderRepository.findAll(); }

    public Order getOrderById(String id){ return orderRepository.findById(id).orElse(null); }

    public Order createOrder(Order order){
        if(order.getOrderStatus() == null) order.setOrderStatus(OrderStatus.PENDING);
        order.setLocalDateTime(LocalDateTime.now());
        order.setTotalPrice(order.getQuantity() * order.getTotalPrice());
        return orderRepository.save(order);
    }

    public Order updateOrder(String id, Order order){
        Order existing = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        existing.setCustomerName(order.getCustomerName());
        existing.setProductName(order.getProductName());
        existing.setQuantity(order.getQuantity());
        existing.setTotalPrice(order.getTotalPrice());
        existing.setOrderStatus(order.getOrderStatus());
        return orderRepository.save(existing);
    }

    public void deleteOrder(String id){ orderRepository.deleteById(id); }

    public Order updateOrderStatus(String id, OrderStatus status){
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    // Dashboard Queries
    public int getTodayOrders(){
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime tomorrowStart = todayStart.plusDays(1);
        return orderRepository.findOrdersForToday(todayStart, tomorrowStart).size();
    }

    public double getMonthlyRevenue(){
        LocalDateTime monthStart = YearMonth.now().atDay(1).atStartOfDay();
        return orderRepository.findOrdersForThisMonth(monthStart)
                .stream().mapToDouble(Order::getTotalPrice).sum();
    }
}
