package com.teamVoid.healthRecord.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    private String username;
    private String password = "password";
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<Role> roles;

    @Embedded
    private Profile profile;

    @Embedded
    private Doctor doctor;

    @Embedded
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<PatientCase> patientCases;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor", referencedColumnName = "username")
    private List<Appointment> doctorAppointments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "adminId", referencedColumnName = "username")
    private List<HospitalAdminMap> hospitalAdminMaps;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorId", referencedColumnName = "username")
    private List<HospitalDoctorMap> hospitalDoctorMaps;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorId", referencedColumnName = "username")
    private List<DoctorPatientMap> associatedDoctors;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", referencedColumnName = "username")
    private List<DoctorPatientMap> associatedPatients;

    public User() {
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<PatientCase> getPatientCases() {
        return patientCases;
    }

    public void setPatientCases(List<PatientCase> patientCases) {
        this.patientCases = patientCases;
    }

    public List<Appointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<Appointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    public List<HospitalAdminMap> getHospitalAdminMaps() {
        return hospitalAdminMaps;
    }
}
