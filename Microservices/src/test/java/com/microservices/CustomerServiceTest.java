package com.microservices;

import com.microservices.model.Customer;
import com.microservices.repository.CustomerRepository;
import com.microservices.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
    }

    @Test
    public void testRegisterCustomer() {
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("0123456789");
        customer.setAddress("Street 123");

        Customer saved = customerService.registerCustomer(customer);

        assertNotNull(saved.getId());
        assertEquals("John Doe", saved.getFullName());
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer = new Customer();
        customer.setFullName("Jane Doe");
        customer.setEmail("jane@example.com");
        customer.setPhone("0987654321");
        customer.setAddress("Street 456");
        customerService.registerCustomer(customer);

        List<Customer> customers = customerService.getAllCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() > 0);
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setFullName("Bob Smith");
        customer.setEmail("bob@example.com");
        customer.setPhone("0123009876");
        customer.setAddress("Street 789");
        Customer saved = customerService.registerCustomer(customer);

        Customer found = customerService.getCustomerById(saved.getId());
        assertEquals(saved.getEmail(), found.getEmail());
    }
}

