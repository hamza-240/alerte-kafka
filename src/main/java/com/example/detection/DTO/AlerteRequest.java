package com.example.detection.DTO;


import com.example.detection.Entity.Patient;

public class AlerteRequest {
    private int clickCount;
    private Patient patient;

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
