package com.quiz4.controller;

import com.quiz4.model.Order;
import com.quiz4.model.OrderStatus;
import com.quiz4.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="http://localhost:2222")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> allOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
    }

    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable String id, @RequestParam OrderStatus status){
        return orderService.updateOrderStatus(id,status);
    }

    @GetMapping("/today/count")
    public int todayOrders(){
        return orderService.getTodayOrders();
    }

    @GetMapping("/revenue/month")
    public double monthlyRevenue(){
        return orderService.getMonthlyRevenue();
    }
}
