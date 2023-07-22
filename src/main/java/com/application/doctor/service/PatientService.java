package com.application.doctor.service;

import com.application.doctor.model.Appointment;
import com.application.doctor.model.AuthenticationToken;
import com.application.doctor.model.Patient;
import com.application.doctor.model.dto.SignInInput;
import com.application.doctor.model.dto.SignUpOutput;
import com.application.doctor.repository.IAuthoToken;
import com.application.doctor.repository.IDoctor;
import com.application.doctor.repository.IPatient;
import com.application.doctor.service.utility.emailUtility.EmailHandler;
import com.application.doctor.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatient iPatient;

    @Autowired
    IDoctor iDoctor;

    @Autowired
    AppiontmentService appiontmentService;

    @Autowired
    IAuthoToken iAuthoToken;
    public SignUpOutput signUpPatient(Patient patient) {
        boolean signUpStatus = true;
        String massage=null;

        String email=patient.getPatientEmail();
        if(email== null){
            massage="Invalid email !!";
            signUpStatus=false;
            return new SignUpOutput(signUpStatus,massage);
        }
        Patient existingPatient=iPatient.findFirstByPatientEmail(email);

        if(existingPatient !=null){
            massage="User Already exist";
            signUpStatus= false;
            return new SignUpOutput(signUpStatus, massage);
        }
        try{
            String encryptedPassword= PasswordEncrypter.encryptPassword(patient.getPatientPassword());
            patient.setPatientPassword(encryptedPassword);
            iPatient.save(patient);
            return new SignUpOutput(signUpStatus,"Patient registered Successfully !");
        }catch (Exception e){
            massage="Internal error try again !! ";
            signUpStatus= false;
            return new SignUpOutput(signUpStatus, massage);

        }
    }
    public String signInPatient(SignInInput signInInput){

        String massage=null;

        String email=signInInput.getEmail();
        if(email== null){
            massage="Invalid email !!";
            return massage;
        }
        Patient existingPatient=iPatient.findFirstByPatientEmail(email);

        if(existingPatient ==null){
            massage="User Not exist";
            return massage;

        }

        try{
            String encryptedPassword= PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingPatient.getPatientPassword().equals(encryptedPassword)){
                AuthenticationToken token= new AuthenticationToken(existingPatient);
                iAuthoToken.save(token);
                EmailHandler.sendEmail(email,"email testing",token.getTokenValue());
                return "Token send your mail";
            }else{
                massage= "Invalid Login try again";
                return massage;
            }

        }catch (Exception e){
            massage="Internal error try again !! ";
            return massage;



        }

    }
    public boolean scheduleAppointment(Appointment appointment) {
        Long doctorId = appointment.getDoctors().getDoctorId();
        boolean isdoctorId = iDoctor.existsById(doctorId);
        Long patientId = appointment.getPatient().getPatientId();
        boolean ispatentId = iPatient.existsById(patientId);
        if (isdoctorId && ispatentId) {
            appiontmentService.saveAppointment(appointment);
            return true;
        }else {
            return false;
        }
    }
    public List<Patient> getAllPatient() {
        return iPatient.findAll();
    }

    public void addAppointment(Appointment appointment ) {
    }

    public void cancelAppointment(String email, String token) {

        Patient patient = iPatient.findFirstByPatientEmail(email);
        Appointment appointment = appiontmentService.getAppointmentforPatient(patient);

        appiontmentService.cancelAppointment(appointment);

    }

    public String signOutPatient(String email) {
        Patient patient = iPatient.findFirstByPatientEmail(email);
        iAuthoToken.delete(iAuthoToken.findFirstByPatient(patient));
        return "Patient Signed out Successfully !!";
    }
}
