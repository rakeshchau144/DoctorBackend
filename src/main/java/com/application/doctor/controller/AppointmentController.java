package com.application.doctor.controller;

import com.application.doctor.model.Appointment;
import com.application.doctor.service.AppiontmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    AppiontmentService appointmentServic;


    @GetMapping("appointment/get")
    List<Appointment> getAllappointment(){
        return appointmentServic.getAllAppointment();
    }
}
