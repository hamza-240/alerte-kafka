package com.example.detection.Alerte;

import com.example.detection.Entity.AlerteDTO;
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
    public CustomerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
    @KafkaListener(topics = "pap",groupId = "grp-3")
    public void customerDTO(@Header(KafkaHeaders.RECEIVED_KEY)String id ,@Payload  String message) throws JsonProcessingException {

        AlerteDTO alerteDTO = objectMapper.readValue(message, AlerteDTO.class);
        System.out.println("📩 Message reçu : "+alerteDTO.toString()+" son id : "+id +" cote medcin ");
    }
}
