package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.HospitalAdminMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalAdminMapRepository extends JpaRepository<HospitalAdminMap, Long> {

    List<String> getHospitalIdsByAdminId(String hospAdmin);
}
