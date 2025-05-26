package com.example.detection.jpa;

import com.example.detection.Entity.DME;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Dme_JPA extends JpaRepository<DME,Long> {
}
