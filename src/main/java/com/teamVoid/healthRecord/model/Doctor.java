package com.teamVoid.healthRecord.model;

import javax.persistence.Embeddable;

@Embeddable
public class Doctor {

    private String speciality;

    public Doctor() {
    }

    public Doctor(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
