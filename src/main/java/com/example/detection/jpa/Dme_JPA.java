package com.example.detection.jpa;

import com.example.detection.Entity.DME;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Dme_JPA extends JpaRepository<DME,Long> {
    List<DME> findByPatient_Id(Long id);

}
