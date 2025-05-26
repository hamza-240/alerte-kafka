package com.example.detection.jpa;

import com.example.detection.Entity.Indicateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicateurJPA extends JpaRepository<Indicateur,Long> {
}
