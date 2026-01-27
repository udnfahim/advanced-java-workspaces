package com.quiz4.controller;

import com.quiz4.model.Order;
import com.quiz4.model.OrderStatus;
import com.quiz4.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "")
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
    public Order createOrder(@RequestBody Order order,
                             @CookieValue(value = "JWT", required = false) String token,
                             HttpServletResponse response){

        if(token == null){
            Cookie cookie = new Cookie("JWT", "dummy-jwt-token");
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
        }
        return orderService.createOrder(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable String id, @RequestParam OrderStatus status){
        return orderService.updateOrderStatus(id,status);
    }

    @GetMapping("/today/count")
    public int todayOrders(){
        return orderService.getTodayOrders();
    }

    @GetMapping("/revenue/month")
    public BigDecimal monthlyRevenue(){
        return orderService.getMonthlyRevenue();
    }
}
