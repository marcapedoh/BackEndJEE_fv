package com.example.backend.controller;

import com.example.backend.model.Customer;
import com.example.backend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "admins/customers")

public class AdminCustomerController {

    private final CustomerService customerService;

    public AdminCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomerByName(@RequestParam(required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(customerService.searchCustomersByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}