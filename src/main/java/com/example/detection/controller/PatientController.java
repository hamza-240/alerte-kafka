package com.example.detection.controller;


import com.example.detection.Entity.Patient;
import com.example.detection.Service.PatientService;
import com.example.detection.Service.ServiceAlerte;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.detection.Entity.AlerteRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PatientController {
    @Autowired
    private PatientService patientService;
    private ServiceAlerte serviceAlerte;

    public PatientController(PatientService patientService, ServiceAlerte serviceAlerte) {
        this.patientService = patientService;
        this.serviceAlerte = serviceAlerte;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/authPatient")
    public Patient auth(@RequestBody Patient patient){
        return patientService.exist(patient.getId_patient());

    }

    @PostMapping("/alerte")
    public int handleAlerte(@RequestBody AlerteRequest request) throws JsonProcessingException {
        //int incrementedCount = request.getClickCount();
        Patient patient = request.getPatient();

        int incrementedCount = request.getClickCount() + 1;
        System.out.println("Patient ID : " + request.getPatient().getId() + ", clics : " + incrementedCount);
        serviceAlerte.Alerte(1,request.getPatient().getId_patient());
        return incrementedCount; // Retourne juste l'int
    }
}
