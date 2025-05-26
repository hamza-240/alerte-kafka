package com.example.detection.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date_naissance;

    @OneToMany(mappedBy = "patient")  // "patient" est le nom du champ dans DME qui fait référence à Patient
    private List<DME> dossiersMedicaux;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<SeuilPR> seuilPRS;

    // Constructeurs
    public Patient() {}

    public Patient( LocalDate date_naissance) {

        this.date_naissance = date_naissance;
    }

    // Getters et setters
    public Long getId_patient() {
        return id;
    }

    public void setId_patient(Long id_patient) {
        this.id = id_patient;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public List<DME> getDossiersMedicaux() {
        return dossiersMedicaux;
    }

    public void setDossiersMedicaux(List<DME> dossiersMedicaux) {
        this.dossiersMedicaux = dossiersMedicaux;
    }
}
