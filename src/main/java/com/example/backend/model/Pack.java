package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String offerName;

    @NotNull
    private int durationMonths;

    @NotNull
    private double monthlyPrice;

    public Pack() {
    }

    public Pack(String offerName, int durationMonths, double monthlyPrice) {
        this.offerName = offerName;
        this.durationMonths = durationMonths;
        this.monthlyPrice = monthlyPrice;
    }

}