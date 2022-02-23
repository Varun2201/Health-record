package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.PatientCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientCaseRepository extends JpaRepository<PatientCase, Long> {

    List<PatientCase> getPatientCasesByUsername(String username);
    PatientCase getPatientCaseById(Long id);
}
