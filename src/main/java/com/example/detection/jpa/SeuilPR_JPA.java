package com.example.detection.jpa;

import com.example.detection.Entity.SeuilPR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeuilPR_JPA extends JpaRepository<SeuilPR,Long> {

    @Query("SELECT s FROM SeuilPR s WHERE s.patient.id = :patient_id AND s.indicateur.id = :indicateur_id")
    SeuilPR findSeuilPRByIndicateurIdAndPatientId(@Param("patient_id") long patient_id, @Param("indicateur_id") long indicateur_id);

}
