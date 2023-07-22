package com.application.doctor.controller;

import com.application.doctor.model.Appointment;
import com.application.doctor.model.Patient;
import com.application.doctor.model.dto.SignInInput;
import com.application.doctor.model.dto.SignUpOutput;
import com.application.doctor.service.AuthonticationService;
import com.application.doctor.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated

public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AuthonticationService authonticationService;

    @PostMapping("patient/signup")
    SignUpOutput sinUpPatient(@RequestBody Patient patient){
        return patientService.signUpPatient(patient);
    }

    @PostMapping("patient/signIn")
    String signInPatient(@RequestBody @Valid SignInInput signInInput){
       return patientService.signInPatient(signInInput);
    }

    @DeleteMapping("patient/signOut")
    String signOutPatient(String email, String token){
        if (authonticationService.authenticate(email, token)) {
            return patientService.signOutPatient(email );
        }else{
            return "SignOut failed try again ..";
        }
    }

    @GetMapping("patient/get")
    public List<Patient> getAllPatient(){
        return patientService.getAllPatient();
    }

    @PostMapping("appointment/schedule")
    public String addappointment(@RequestBody Appointment appointment, String email, String token){

        if(authonticationService.authenticate(email,token)) {
           boolean status = patientService.scheduleAppointment(appointment);
            return status? "Appointment scheduled":"error during scheduling ";
        }else{
            return "Appointment not Schedule !!";
        }
    }
    @DeleteMapping("appointment/cancel")
    public String cancelAppointment(String email, String token) {

        if (authonticationService.authenticate(email, token)) {
            patientService.cancelAppointment(email,token);
            return "Appointment scheduled ";
        } else {
            return "Appointment not Schedule !!";
        }
    }
}
