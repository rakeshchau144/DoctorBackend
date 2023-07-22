package com.application.doctor.service;

import com.application.doctor.model.AuthenticationToken;
import com.application.doctor.repository.IAuthoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthonticationService {

    @Autowired
    IAuthoToken iAuthoToken;
    public boolean authenticate(String email, String token){

        AuthenticationToken authenticationToken=iAuthoToken.findFirstByTokenValue(token);
        if(authenticationToken==null){
            return false;
        }
        String tokenEmail = authenticationToken.getPatient().getPatientEmail();
        return tokenEmail.equals(email);

    }
}
