package com.application.doctor.service;

import com.application.doctor.model.Appointment;
import com.application.doctor.model.Patient;
import com.application.doctor.repository.IAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppiontmentService {
    @Autowired
    IAppointment iAppointment;

    public List<Appointment> getAllAppointment() {
       return iAppointment.findAll();
    }

    public void saveAppointment(Appointment appointment) {
        appointment.setAppoinmentScheduleTime(LocalDateTime.now());
        iAppointment.save(appointment);
    }

    public Appointment getAppointmentforPatient(Patient patient) {
        return iAppointment.findFirstByPatient(patient);
    }

    public void cancelAppointment(Appointment appointment) {
        iAppointment.delete(appointment);
    }
}
