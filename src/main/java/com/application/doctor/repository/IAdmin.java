package com.application.doctor.repository;

import com.application.doctor.model.Admin;
import com.application.doctor.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdmin extends CrudRepository<Admin,Long> {
}
