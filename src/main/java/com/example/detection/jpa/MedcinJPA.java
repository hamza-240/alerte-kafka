package com.example.detection.jpa;

import com.example.detection.Entity.Medcin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedcinJPA extends JpaRepository<Medcin,Long> {
}
