package com.application.doctor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;

    @OneToOne
    @JoinColumn(name = "fk_patientId")
    private Patient patient;

    public AuthenticationToken(Patient patient) {
        this.patient = patient;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime=LocalDateTime.now();
    }
}
