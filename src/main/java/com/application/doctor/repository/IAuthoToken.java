package com.application.doctor.repository;

import com.application.doctor.model.AuthenticationToken;
import com.application.doctor.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthoToken extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByTokenValue(String token);

    AuthenticationToken findFirstByPatient(Patient patient);
}
