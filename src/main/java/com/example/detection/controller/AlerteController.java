package com.example.detection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// AlerteController.java
@RestController
public class AlerteController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send-alerte")
    public ResponseEntity<String> sendAlerte(@RequestBody AlerteRequest request) {


        messagingTemplate.convertAndSendToUser(
                request.getUserId(),
                "/queue/alertes",
                "Nouvelle alerte: " + request.getMessage()
        );
        return ResponseEntity.ok("Alerte envoyée");
    }
}