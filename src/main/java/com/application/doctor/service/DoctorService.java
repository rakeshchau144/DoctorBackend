package com.application.doctor.service;

import com.application.doctor.model.Doctor;
import com.application.doctor.repository.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctor iDoctor;
    public void addDoctor(Doctor doctor) {
        iDoctor.save(doctor);
    }

    public List<Doctor> getAllDoctor() {
        return iDoctor.findAll();
    }
}
