package com.teamVoid.healthRecord.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hospital {

    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital", referencedColumnName = "id")
    List<Appointment> appointments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospitalId", referencedColumnName = "id")
    List<HospitalAdminMap> hospitalAdminMaps;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospitalId", referencedColumnName = "id")
    List<HospitalDoctorMap> hospitalDoctorMaps;

    public Hospital() {
    }

    public Hospital(String id, String name, String address, String city, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<HospitalAdminMap> getHospitalAdminMaps() {
        return hospitalAdminMaps;
    }
}
