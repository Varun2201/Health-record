package com.teamVoid.healthRecord.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PatientCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_case", referencedColumnName = "id")
    List<Appointment> appointments;

    public PatientCase() {
    }

    public PatientCase(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
