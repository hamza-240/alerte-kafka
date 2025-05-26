package com.example.detection.jpa;

import com.example.detection.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientJPA extends JpaRepository<Patient,Long> {
}
