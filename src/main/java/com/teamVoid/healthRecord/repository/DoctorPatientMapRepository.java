package com.teamVoid.healthRecord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamVoid.healthRecord.model.DoctorPatientMap;

@Repository
public interface DoctorPatientMapRepository extends JpaRepository<DoctorPatientMap, Long> {
    List<DoctorPatientMap> getPatientIdsByDoctorId(String doctorId);
}
