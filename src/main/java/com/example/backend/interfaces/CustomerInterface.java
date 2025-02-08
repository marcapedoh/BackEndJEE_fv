package com.example.backend.service;

import com.example.backend.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerInterface {
    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id);

    List<Customer> searchCustomersByName(String name);

    Customer updateCustomer(Long id, Customer customerDetails);

    void deleteCustomer(Long id);
}
