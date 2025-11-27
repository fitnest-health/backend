package com.fitnest.webbackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private BigDecimal price;
    private String currency; // azn usd eur

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // qida meshq plani ves.
}