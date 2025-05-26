package com.example.detection.jpa;

import com.example.detection.Entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatJPA extends JpaRepository<Resultat,Long> {
}
