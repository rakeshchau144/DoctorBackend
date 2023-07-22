package com.application.doctor.repository;

import com.application.doctor.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface IPatient extends JpaRepository<Patient,Long> {
    Patient findFirstByPatientEmail(String email);
}
