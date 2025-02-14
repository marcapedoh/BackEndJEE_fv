package com.example.backend.controller;

import com.example.backend.model.Customer;
import com.example.backend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "users/customers")

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomerByName(@RequestParam(required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(customerService.searchCustomersByName(name));
    }

}