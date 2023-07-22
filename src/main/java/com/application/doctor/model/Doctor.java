package com.application.doctor.model;
import com.application.doctor.model.enums.Qualification;
import com.application.doctor.model.enums.Specialization;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Doctor.class, property = "doctorId")
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    @Enumerated(EnumType.STRING)
    private Specialization doctorSpecialization;
    @Pattern(regexp = "^[0-9]{10}$")
    private String doctorContactNo;
    @Enumerated(EnumType.STRING)
    private Qualification doctorQualification;
    @Min(value = 200)
    @Max(value = 1000)
    private Integer doctorFee;

    @OneToMany(mappedBy = "doctors")
    private List<Appointment> appointments;

}
