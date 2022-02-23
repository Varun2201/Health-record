package com.teamVoid.healthRecord.service;

import com.teamVoid.healthRecord.model.PatientCase;
import com.teamVoid.healthRecord.repository.PatientCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientCaseService {

    private final PatientCaseRepository patientCaseRepository;

    @Autowired
    public PatientCaseService(PatientCaseRepository patientCaseRepository) {
        this.patientCaseRepository = patientCaseRepository;
    }

    public List<PatientCase> getPatientCases() {
        return patientCaseRepository.findAll();
    }

    public PatientCase getPatientCase(Long id) {
        return patientCaseRepository.getOne(id);
    }

    public List<PatientCase> getPatientCasesByUsername(String username) {
        return patientCaseRepository.getPatientCasesByUsername(username);
    }

    public PatientCase getPatientCase(String username, Long id) {
        PatientCase patientCase = patientCaseRepository.getPatientCaseById(id);
        if (!patientCase.getUsername().equals(username)) {
            throw new IllegalArgumentException(username + " not authorized to view case:" + id);
        }
        return patientCase;
    }

    public String addPatientCase(PatientCase patientCase) {
        try {
            patientCaseRepository.save(patientCase);
        } catch (Exception e) {
            throw new IllegalArgumentException(patientCase.getUsername() + " not found");
        }
        return "case id:" + patientCase.getId() + " added to " + patientCase.getUsername();
    }

    public String deletePatientCase(Long id) {
        patientCaseRepository.deleteById(id);
        return "case:" + id + " deleted";
    }
}
