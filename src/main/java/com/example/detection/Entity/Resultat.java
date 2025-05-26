package com.example.detection.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valeur;
    private LocalDate dateMesure;
    @ManyToOne
    @JoinColumn(name = "dme_id")
    private DME dossier;
    @ManyToOne
    @JoinColumn(name = "indicateur_id")
    private Indicateur indicateur;


    public Resultat() {
    }

    public Resultat(double valeur, LocalDate dateMesure, DME dossier, Indicateur indicateur) {
        this.valeur = valeur;
        this.dateMesure = dateMesure;
        this.dossier = dossier;
        this.indicateur = indicateur;
    }

    public void setDossier(DME dossier) {
        this.dossier = dossier;
    }

    public void setDateMesure(LocalDate dateMesure) {
        this.dateMesure = dateMesure;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public void setId_resultat(Long id_resultat) {
        this.id = id_resultat;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public Long getId_resultat() {
        return id;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public DME getDossier() {
        return dossier;
    }

    public LocalDate getDateMesure() {
        return dateMesure;
    }

    public double getValeur() {
        return valeur;
    }
}
