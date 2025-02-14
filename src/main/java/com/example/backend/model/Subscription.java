package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @JsonIgnore
    @Column(name = "pack_id", nullable = false)
    private Long packId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private boolean enabled;

    @Transient
    private Customer customer;

    @Transient
    private Pack pack;

    public Subscription() {
    }

    public Subscription(Long customerId, Long packId, LocalDate startDate) {
        this.customerId = customerId;
        this.packId = packId;
        this.startDate = startDate;
    }

}