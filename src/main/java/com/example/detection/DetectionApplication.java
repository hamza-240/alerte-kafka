package com.example.detection;

import com.example.detection.Entity.SeuilPR;
import com.example.detection.jpa.SeuilPR_JPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DetectionApplication implements CommandLineRunner {
	@Autowired
	private SeuilPR_JPA seuilPRJpa;

	public static void main(String[] args) {
		SpringApplication.run(DetectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long id_p = 1L;
		long id_d=1L;
		SeuilPR seuilPR = seuilPRJpa.findSeuilPRByIndicateurIdAndPatientId(id_p, id_d);
		System.out.println("Résultat au démarrage : " + seuilPR.toString());

	}
}
