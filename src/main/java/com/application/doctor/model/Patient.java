package com.application.doctor.model;

import com.application.doctor.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Patient.class, property = "patientId")
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientName;
    @Pattern(regexp = "^.+@gmail\\.com$")
    @Column(unique = true)
    private String patientEmail;
    @NotBlank
    private  String patientPassword;
    private Integer patientAge;
    private String patientAddress;
    @Enumerated(EnumType.STRING)
    private Gender patientGender;

    @OneToOne(mappedBy = "patient")
    private Appointment appointment;



}
