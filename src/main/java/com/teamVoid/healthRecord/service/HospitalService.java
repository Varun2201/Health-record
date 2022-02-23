package com.teamVoid.healthRecord.service;

import com.teamVoid.healthRecord.model.Hospital;
import com.teamVoid.healthRecord.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public String addHospital(Hospital hospital) {
        Hospital repoHospital = hospitalRepository.getHospitalByName(hospital.getName());
        if (repoHospital != null && repoHospital.getCity().equals(hospital.getCity())) {
            throw new IllegalArgumentException(hospital.getName() + " in " + hospital.getCity() + " already exists");
        }
        hospitalRepository.save(hospital);
        return hospital.getName() + " added with id:" + hospital.getId();
    }

    public String updateHospital(String id, Hospital hospital) {
        if (hospitalRepository.getHospitalById(id) == null) {
            throw new IllegalArgumentException("hospital with id:" + id + " doesn't exist");
        }
        hospital.setId(id);
        hospitalRepository.save(hospital);
        return "id:" + hospital.getId() + " updated";
    }
    public Hospital getHospital(String id) {
        return hospitalRepository.getHospitalById(id);
    }

    public String deleteHospital(String id) {
        if (hospitalRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("hospital with id:" + id + " doesn't exist");
        }
        hospitalRepository.deleteById(id);
        return "id:" + id + " deleted";
    }
}
