package com.teamVoid.healthRecord.model;

import javax.persistence.Embeddable;

@Embeddable
public class Patient {

    private String height;
    private String weight;
    private String bloodGroup;

    public Patient() {
    }

    public Patient(String height, String weight, String bloodGroup) {
        this.height = height;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
