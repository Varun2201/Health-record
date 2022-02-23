package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.HospitalDoctorMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalDoctorMapRepository extends JpaRepository<HospitalDoctorMap, Long> {

    List<HospitalDoctorMap> getHospitalsByDoctorId(String doctorId);
}
