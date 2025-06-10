package com.example.detection.Service;

import com.example.detection.Entity.DME;
import com.example.detection.jpa.Dme_JPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DmeService {

    @Autowired
    private Dme_JPA dme_jpa;

    public List<DME> allDmePatient(Long id_patient){
        return dme_jpa.findByPatient_Id(id_patient);
    }
}
