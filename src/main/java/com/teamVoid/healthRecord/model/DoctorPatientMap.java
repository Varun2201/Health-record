package com.teamVoid.healthRecord.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DoctorPatientMap {

    @Id
    @GeneratedValue
    private Long id;
    private String doctorId;
    private String patientId;

    public DoctorPatientMap() {
    }

    public DoctorPatientMap(String doctorId, String patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
