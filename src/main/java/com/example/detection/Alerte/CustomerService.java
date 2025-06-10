package com.example.detection.Alerte;

import com.example.detection.DTO.AlerteDTO;
import com.example.detection.Entity.Medcin;
import com.example.detection.Entity.Patient;
import com.example.detection.Service.MedcinService;
import com.example.detection.Service.NotificationService;
import com.example.detection.Service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final ObjectMapper objectMapper ;

    private  PatientService patientService;
    private NotificationService notificationService;
    private MedcinService medcinService;



    public CustomerService(ObjectMapper objectMapper, PatientService patientService, NotificationService notificationService, MedcinService medcinService) {
        this.objectMapper = objectMapper;
        this.patientService = patientService;
        this.notificationService = notificationService;
        this.medcinService = medcinService;
    }

    // L’annotation @KafkaListener fait que Spring Kafka écoute automatiquement le topic "alerts".
    // Dès qu’un message y arrive, Spring appelle automatiquement la méthode recevoir() avec le contenu du message.
    @KafkaListener(topics = "alert1",groupId = "grp-alert")
    public void customer(String message){

        System.out.println("📩 Message reçu : " + message);
    }

    @KafkaListener(topics = "alerte2",groupId = "grp-2")
    public void customer(@Header(KafkaHeaders.RECEIVED_KEY)String id , @Payload String message){
        System.out.println("📩 Message reçu : "+message+" son id : "+id );
    }
    @KafkaListener(topics = "alerte-medcin",groupId = "grp-5")
    public void customerDTOMedcin(@Header(KafkaHeaders.RECEIVED_KEY)String id ,@Payload  String message) throws JsonProcessingException {

        AlerteDTO alerteDTO = objectMapper.readValue(message, AlerteDTO.class);
        String alerte ="📩 Message reçu : "+alerteDTO.toString()+" son id : "+id +" cote medcin ";
        System.out.println(alerte);
        Medcin medcin = medcinService.exist(Long.valueOf(id));
        if (medcin!=null)
            notificationService.notification(medcin,"medcin",medcin.getId().toString(),alerte);



    }
    @KafkaListener(topics = "alerte-patient",groupId = "grp-3")
    public void customerDTOPatient(@Header(KafkaHeaders.RECEIVED_KEY)String id ,@Payload  String message) throws JsonProcessingException {

        AlerteDTO alerteDTO = objectMapper.readValue(message, AlerteDTO.class);
        String alerte = "📩 Message reçu : "+alerteDTO.toString()+" son id : "+id +" cote patient ";
        System.out.println(alerte);
        Patient patient = patientService.exist(Long.valueOf(id));
        if (patient!=null)
            notificationService.notification(patient,"patient",patient.getId_patient().toString(),alerteDTO.toString());

    }
}
