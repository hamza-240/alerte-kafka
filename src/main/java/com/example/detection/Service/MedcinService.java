package com.example.detection.Service;

import com.example.detection.Entity.Medcin;
import com.example.detection.jpa.MedcinJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedcinService {
    @Autowired
    private MedcinJPA medcinJPA;

    public Medcin exist(Long id){
        return medcinJPA.findById(id).orElse(null);
    }
}
