package com.example.detection;

import com.example.detection.Alerte.ProducerService;
import com.example.detection.Entity.Resultat;
import com.example.detection.Service.ServiceAlerte;
import com.example.detection.jpa.ResultatJPA;
import com.example.detection.jpa.SeuilPR_JPA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DetectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetectionApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(SeuilPR_JPA seuilPRJpa,
											   ServiceAlerte serviceAlerte,
											   ResultatJPA resultatJPA,
											   ProducerService producerService) {
		return args -> {
			long id_p = 1L;
			long id_d = 1L;
			//SeuilPR seuilPR = seuilPRJpa.findSeuilPRByIndicateurIdAndPatientId(id_p, id_d);
			//System.out.println("Résultat au démarrage : " + seuilPR.toString());
			serviceAlerte.Alerte(1, 1);
			Resultat resultat = resultatJPA.findById(1L).orElse(null);
			System.out.println(resultat);
			producerService.producer(1, "HHOOOOOPPPPPPPPPPPP");
		};
	}
}