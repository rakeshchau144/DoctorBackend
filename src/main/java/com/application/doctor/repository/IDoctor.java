package com.application.doctor.repository;

import com.application.doctor.model.Doctor;
import com.application.doctor.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctor extends JpaRepository<Doctor,Long> {
}
