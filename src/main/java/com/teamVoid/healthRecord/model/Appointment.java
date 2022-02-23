package com.teamVoid.healthRecord.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String doctor;
    private String hospital;
    @Column(name = "patient_case")
    private Long patientCase;
    private LocalDateTime time;
    private String diagnosis;
    private String prescription;
    private String treatment;

    @Lob
    private byte[] report;

    public Appointment() {
        this.time = LocalDateTime.now();
    }

    public Appointment(String hospital, Long patientCase, String diagnosis, String prescription, String treatment) {
        this();
        this.hospital = hospital;
        this.patientCase = patientCase;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.treatment = treatment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Long getPatientCase() {
        return patientCase;
    }

    public void setPatientCase(Long patientCase) {
        this.patientCase = patientCase;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public byte[] getReport() {
        return report;
    }

    public void setReport(byte[] report) {
        this.report = report;
    }
}
