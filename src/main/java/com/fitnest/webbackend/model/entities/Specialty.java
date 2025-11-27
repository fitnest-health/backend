package com.fitnest.webbackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "specialties")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // sahesinin adi, fitness yoga ves

    private String iconUrl; // ola biler iconu olsun
}