package com.application.doctor.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Appointment.class, property = "appoinmentId")
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appoinmentId;
    private String appoinmentDesc;
    private LocalDateTime appoinmentScheduleTime;
    private LocalDateTime appoinmentBookingTime;

    @OneToOne
    @JoinColumn(name = "fk_patient_Id")
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "fk_doctor_id")
    private Doctor doctors;


}
