package com.teamVoid.healthRecord.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HospitalAdminMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String hospitalId;
    private String adminId;

    public HospitalAdminMap() {
    }

    public HospitalAdminMap(String hospitalId, String adminId) {
        this.hospitalId = hospitalId;
        this.adminId = adminId;
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
