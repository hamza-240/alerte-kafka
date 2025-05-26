package com.example.detection.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medcin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom ;

    @OneToMany(mappedBy = "medcin")
    @JsonManagedReference
    private List<SeuilPR> seuilPRS;

    public Medcin() {
    }

    public Medcin(String nom) {

        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public List<SeuilPR> getSeuilPRS() {
        return seuilPRS;
    }

    public String getNom() {
        return nom;
    }

    public void setSeuilPRS(List<SeuilPR> seuilPRS) {
        this.seuilPRS = seuilPRS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
