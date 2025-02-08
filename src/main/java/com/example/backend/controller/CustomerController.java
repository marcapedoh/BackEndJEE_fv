package com.example.backend.controller;

import com.example.backend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")

public class CustomerController {

    @Autowired
    private com.example.backend.service.CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody com.example.backend.model.Customer customer) {
        com.example.backend.model.Customer createdCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<List<com.example.backend.model.Customer>> getAllCustomers() {
        List<com.example.backend.model.Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<com.example.backend.model.Customer>> searchCustomerByName(@PathVariable String name) {
        List<com.example.backend.model.Customer> customers = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.backend.model.Customer> updateCustomer(@PathVariable Long id, @RequestBody com.example.backend.model.Customer customer) {
        com.example.backend.model.Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}