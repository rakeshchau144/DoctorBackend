package com.application.doctor.repository;

import com.application.doctor.model.Appointment;
import com.application.doctor.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointment extends JpaRepository<Appointment,Long> {
    Appointment findFirstByPatient(Patient patient);
}
