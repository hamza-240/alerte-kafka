package com.example.detection.Service;


import com.example.detection.Entity.Medcin;
import com.example.detection.Entity.Patient;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // injecter la classe SimpMessagingTemplate
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notification(Object object,String userCible,String idUser,String messageEnvoye){

        String message ="";
        // declarer le topic ou celui que vous voulez
        String destination = "/topic/alertes/"+userCible+"/"+idUser;


        if(userCible == "patient") {
            Patient patient = (Patient) object;

            // Si vous envoyez l'objet Client, assurez-vous qu'il n'y a pas de cycles infinis (ex: relation bidirectionnelle sans @JsonIgnore)
             message = "Un nouveau alerte  a été declanché  : " + patient.getId_patient() + " " + patient.getDate_naissance();
        }else if (userCible=="medcin"){

            Medcin medcin = (Medcin) object;
             message = "Un nouveau alerte  a été declanché  : "+medcin.getId() +" "+medcin.getNom();
        }

        System.out.println("Envoi d'une notification WebSocket à " + destination + ": " + message);
        simpMessagingTemplate.convertAndSend(destination, messageEnvoye); // Ou newClient si vous envoyez l'objet
    }
}