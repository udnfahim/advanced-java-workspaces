package com.microservices.service;

import com.microservices.model.Item;
import com.microservices.model.Order;
import com.microservices.repository.CustomerRepository;
import com.microservices.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final String CUSTOMER_SERVICE_URL = "http://localhost:8080/customers/";

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        validateOrder(order);

        if (!customerRepository.existsById(Math.toIntExact(order.getCustomerId()))) {
            throw new RuntimeException("Customer not found");
        }

        double total = order.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        order.setTotalAmount(total);

        order.setStatus("PLACED");
        return orderRepository.save(order);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order updateOrderStatus(String id, String status) {
        Order order = getOrderById(id);
        if (status == null || status.trim().isEmpty()) {
            throw new RuntimeException("Invalid status");
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    private void validateOrder(Order order) {
        if (order.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required");
        }
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("At least one item is required");
        }
        for (Item item : order.getItems()) {
            if (item.getProductName() == null || item.getProductName().trim().isEmpty()) {
                throw new RuntimeException("Item name is required");
            }
            if (item.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be greater than 0");
            }
            if (item.getPrice() < 0) {
                throw new RuntimeException("Price cannot be negative");
            }
        }
    }
}
