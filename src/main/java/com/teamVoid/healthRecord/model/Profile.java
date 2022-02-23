package com.teamVoid.healthRecord.model;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;

@Embeddable
public class Profile {

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String city;
    private String phone;

    @Transient
    private Integer age;

    @Lob
    private byte[] image;

    public Profile() {
    }

    public Profile(String firstName, String lastName, LocalDate dob, String address, String city, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
