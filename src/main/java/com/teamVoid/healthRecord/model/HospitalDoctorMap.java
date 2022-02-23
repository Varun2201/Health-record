package com.teamVoid.healthRecord.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HospitalDoctorMap {

    @Id
    @GeneratedValue
    private Long id;
    private String hospitalId;
    private String doctorId;

    public HospitalDoctorMap() {
    }

    public HospitalDoctorMap(String hospitalId, String doctorId) {
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
