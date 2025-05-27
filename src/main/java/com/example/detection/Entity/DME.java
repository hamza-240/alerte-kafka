package com.example.detection.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class DME {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date_creation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference("patient-dossiers")
    // nom de la colonne clé étrangère dans la table DME
    private Patient patient;

    @OneToMany(mappedBy = "dossier")
    @JsonManagedReference("dme-resultats")
    private List<Resultat> resultats;
    // Constructeurs
    public DME() {}

    public DME(Long id, LocalDate date_creation, Patient patient) {
        this.id = id;
        this.date_creation = date_creation;
        this.patient = patient;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // toString (optionnel)
    @Override
    public String toString() {
        return "DME{" +
                "id=" + id +
                ", date_creation=" + date_creation +
                ", patient=" + patient +
                '}';
    }
}

