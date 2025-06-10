package com.example.detection.Service;

import com.example.detection.Entity.Patient;
import com.example.detection.jpa.PatientJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService {

    @Autowired
    private PatientJPA patientJPA;

    public Patient exist(Long id){
        return patientJPA.findById(id).orElse(null);
    }
    public List<Patient> getAllPatients() {
        return patientJPA.findAll();
    }
}
