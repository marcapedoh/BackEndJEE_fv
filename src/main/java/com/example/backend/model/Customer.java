package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "active_subscription", nullable = false)
    private boolean activeSubscription;

    // Getters and Setters

}
