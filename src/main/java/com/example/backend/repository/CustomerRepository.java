package com.example.backend.repository;

import com.example.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
    List<Customer> findByLastName(String lastName);
}