package com.fitnest.webbackend.model.entities;

import com.fitnest.webbackend.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    private String fullName;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    private String phoneNumber;
    private String email;
    private String address;

    // ish saatlari
    private String workingHoursWeekdays;
    private String workingHoursWeekend;

    // sosial media
    private String instagramUrl;
    private String facebookUrl;
    private String linkedinUrl;



    // Bir məşqçinin bir neçə ixtisası ola bilər
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "coach_specialties",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties;

    // hansi mesq zalinda ishleyir, yeqin lazim olacaq
    private String gymName; // "FitZone Gym"
}