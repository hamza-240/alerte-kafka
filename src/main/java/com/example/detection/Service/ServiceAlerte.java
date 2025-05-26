package com.example.detection.Service;

import com.example.detection.Entity.DME;
import com.example.detection.Entity.Resultat;
import com.example.detection.Entity.SeuilPR;
import com.example.detection.jpa.Dme_JPA;
import com.example.detection.jpa.ResultatJPA;
import com.example.detection.jpa.SeuilPR_JPA;

import java.util.List;

public class ServiceAlerte {
    private Dme_JPA dme_jpa;
    private SeuilPR_JPA seuilPRJpa;
    private ResultatJPA resultatJPA;

    public void Alerte(long id,long idPatient){
        long id_ind;
        double valeur;
        double seuilMin;
        double seuilMax;
        DME dme = dme_jpa.findById(id).orElse(null);
        if (dme!=null){
            List<Resultat>  resultats = dme.getResultats();
            if (resultats!=null){
                for (Resultat resultat:resultats){
                    valeur = resultat.getValeur();
                    id_ind = resultat.getIndicateur().getId_indicateur();
                    SeuilPR seuilPR =seuilPRJpa.findSeuilPRByIndicateurIdAndPatientId(idPatient,id_ind) ;
                    if(seuilPR != null){
                        // affecter seui min et seuilMAX
                        // comparer les seuils avec la valeur
                        // si il y a une anomalie declancher une alerte
                        // le package alerte contient les deux methode proder et customer
                    }else {
                        // tu doit faire le meme travaille mais cette fois en utilsant le tableau seuil par defaut

                    }
                }
            }
        }
    }
}
