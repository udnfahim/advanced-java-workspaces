package com.microservices;

import com.microservices.model.Customer;
import com.microservices.model.Item;
import com.microservices.model.Order;
import com.microservices.repository.CustomerRepository;
import com.microservices.repository.OrderRepository;
import com.microservices.service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Customer testCustomer;

    @BeforeEach
    public void setup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();

        testCustomer = new Customer();
        testCustomer.setFullName("Alice");
        testCustomer.setEmail("alice" + System.currentTimeMillis() + "@example.com");
        testCustomer.setPhone("09" + (10000000 + (int)(Math.random() * 89999999)));
        testCustomer.setAddress("Street 456");

        testCustomer = customerRepository.save(testCustomer);
    }

    @AfterEach
    public void cleanup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testCreateOrder() {
        Item item1 = new Item();
        item1.setProductName("Book");
        item1.setPrice(50);
        item1.setQuantity(2);

        Item item2 = new Item();
        item2.setProductName("Pen");
        item2.setPrice(10);
        item2.setQuantity(3);

        Order order = new Order();
        order.setCustomerId(testCustomer.getId());
        order.setItems(Arrays.asList(item1, item2));

        Order saved = orderService.createOrder(order);

        assertNotNull(saved.getId());
        assertEquals(50*2 + 10*3, saved.getTotalAmount());
        assertEquals("PLACED", saved.getStatus());
        assertEquals(2, saved.getItems().size());
    }

    @Test
    public void testGetOrdersByCustomer() {
        Item item = new Item();
        item.setProductName("Notebook");
        item.setPrice(20);
        item.setQuantity(5);

        Order order = new Order();
        order.setCustomerId(testCustomer.getId());
        order.setItems(Arrays.asList(item));

        orderService.createOrder(order);

        List<Order> orders = orderService.getOrdersByCustomer(testCustomer.getId());
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(testCustomer.getId(), orders.get(0).getCustomerId());
    }

    @Test
    public void testCreateOrderWithoutItems() {
        Order order = new Order();
        order.setCustomerId(testCustomer.getId());
        order.setItems(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(order);
        });
        assertEquals("At least one item is required", exception.getMessage());
    }

    @Test
    public void testCreateOrderInvalidCustomer() {
        Item item = new Item();
        item.setProductName("Laptop");
        item.setPrice(500);
        item.setQuantity(1);

        Order order = new Order();
        order.setCustomerId(99999L);
        order.setItems(Arrays.asList(item));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(order);
        });
        assertEquals("Customer not found", exception.getMessage());
    }
}
