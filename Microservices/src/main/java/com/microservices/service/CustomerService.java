package com.microservices.service;

import com.microservices.model.Customer;
import com.microservices.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public Customer registerCustomer(Customer customer) {

        validateCustomer(customer);

        if (repository.existsByEmail(customer.getEmail())) {

            throw new RuntimeException("Email already exists");
        }

        return repository.save(customer);
    }

    public Customer getCustomerById(Long id) {

        return repository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer updateCustomer(Long id, Customer customer) {

        Customer existing = getCustomerById(id);
        validateCustomer(customer);

        existing.setFullName(customer.getFullName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setAddress(customer.getAddress());

        return repository.save(existing);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    private void validateCustomer(Customer c) {

        if (c.getFullName() == null || c.getFullName().trim().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }
        if (c.getEmail() == null || !c.getEmail().contains("@")) {
            throw new RuntimeException("Valid email is required");
        }
        if (c.getPhone() == null || c.getPhone().trim().isEmpty()) {
            throw new RuntimeException("Phone is required");
        }
    }
}
