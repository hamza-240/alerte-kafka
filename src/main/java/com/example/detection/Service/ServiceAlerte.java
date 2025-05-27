package com.example.detection.Service;

import com.example.detection.Alerte.CustomerService;
import com.example.detection.Alerte.KafkaConstants;
import com.example.detection.Alerte.ProducerService;
import com.example.detection.Entity.AlerteDTO;
import com.example.detection.Entity.DME;
import com.example.detection.Entity.Resultat;
import com.example.detection.Entity.SeuilPR;
import com.example.detection.jpa.Dme_JPA;
import com.example.detection.jpa.ResultatJPA;
import com.example.detection.jpa.SeuilPR_JPA;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAlerte {
    private Dme_JPA dme_jpa;
    private SeuilPR_JPA seuilPRJpa;
    private ResultatJPA resultatJPA;
    private ProducerService producer;
    private CustomerService consumer ;

    public ServiceAlerte(CustomerService consumer, ProducerService producer, ResultatJPA resultatJPA, SeuilPR_JPA seuilPRJpa, Dme_JPA dme_jpa) {
        this.consumer = consumer;
        this.producer = producer;
        this.resultatJPA = resultatJPA;
        this.seuilPRJpa = seuilPRJpa;
        this.dme_jpa = dme_jpa;
    }

    @Transactional
    public void Alerte(long id, long idPatient) throws JsonProcessingException {
        long id_ind;
        double valeur;
        double seuilMin;
        double seuilMax;
        AlerteDTO alerteDTO=new AlerteDTO();
        DME dme = dme_jpa.findById(id).orElse(null);
        System.out.println("1 - ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ");
        if (dme!=null){
            List<Resultat>  resultats = dme.getResultats();
            if (resultats!=null){
                System.out.println("2 - ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ");
                for (Resultat resultat:resultats){
                    System.out.println("3 - ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ");
                    System.out.println("=======\n======");
                    valeur = resultat.getValeur();
                    id_ind = resultat.getIndicateur().getId_indicateur();
                    SeuilPR seuilPR =seuilPRJpa.findSeuilPRByIndicateurIdAndPatientId(idPatient,id_ind) ;
                    alerteDTO.setResultat(resultat);

                    if(seuilPR != null){
                        // affecter seui min et seuilMAX
                        seuilMin = seuilPR.getSeuiMin();
                        seuilMax = seuilPR.getSeuilMax();
                        // comparer les seuils avec la valeur
                        System.out.println("4 - ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ");
                        if(valeur > seuilMax || valeur < seuilMin){
                            // si il y a une anomalie declancher une alerte
                            alerteDTO.setSeuilPR(seuilPR);
                            System.out.println("5 - ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ");
                            //producer.producer(seuilPR.getMedcin().getId(),alerteDTO, KafkaConstants.TOPIC_ALERT_MEDCIN);
                            //producer.producer(seuilPR.getPatient().getId_patient(),alerteDTO,KafkaConstants.TOPIC_ALERT_PATIENT);
                            producer.producer(seuilPR.getMedcin().getId(),alerteDTO);
                            //producer.producer(seuilPR.getPatient().getId_patient(),alerteDTO,KafkaConstants.TOPIC_ALERT_MEDCIN);
                        }

                        // le package alerte contient les deux methode proder et customer
                    }else {
                        // tu doit faire le meme travaille mais cette fois en utilsant le tableau seuil par defaut
                        // mais cette fois il ne declanche pas une alerte en temps reel
                        // il va le stocker dans une tables et le consulte plus tard
                        // tu doit planifier le declanchement des ses alert pour une temps precise chaque jour


                    }
                }
            }
        }
    }
}
