package com.teamVoid.healthRecord.service;

import com.teamVoid.healthRecord.model.DoctorPatientMap;
import com.teamVoid.healthRecord.model.HospitalAdminMap;
import com.teamVoid.healthRecord.model.HospitalDoctorMap;
import com.teamVoid.healthRecord.repository.DoctorPatientMapRepository;
import com.teamVoid.healthRecord.repository.HospitalAdminMapRepository;
import com.teamVoid.healthRecord.repository.HospitalDoctorMapRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {

    private final DoctorPatientMapRepository doctorPatientMapRepository;
    private final HospitalAdminMapRepository hospitalAdminMapRepository;
    private final HospitalDoctorMapRepository hospitalDoctorMapRepository;

    public MappingService(DoctorPatientMapRepository doctorPatientMapRepository, HospitalAdminMapRepository hospitalAdminMapRepository, HospitalDoctorMapRepository hospitalDoctorMapRepository) {
        this.doctorPatientMapRepository = doctorPatientMapRepository;
        this.hospitalAdminMapRepository = hospitalAdminMapRepository;
        this.hospitalDoctorMapRepository = hospitalDoctorMapRepository;
    }


    public boolean checkPatientDoctor(String principal, String username) {
        List<DoctorPatientMap> patientMaps = doctorPatientMapRepository.getPatientIdsByDoctorId(principal);
        List<String> patients = new ArrayList<>();
        for (DoctorPatientMap i:patientMaps) {
            patients.add(i.getPatientId());
        }
        return patients.contains(username);
    }

    public boolean checkAdminHospital(String hospAdmin, String hosp) {
        List<String> hospitals = hospitalAdminMapRepository.getHospitalIdsByAdminId(hospAdmin);
        return hospitals.contains(hosp);
    }

    public boolean checkHospitalDoctorMap(String hospitalId, String doctorId) {
        List<HospitalDoctorMap> hospitals = hospitalDoctorMapRepository.getHospitalsByDoctorId(doctorId);
        for(HospitalDoctorMap i: hospitals)
        {
            if ((i.getDoctorId()).equals(doctorId))
            {
                return true;
            }
        }
        return false;
    }

    public String addDoctorPatientMap(DoctorPatientMap doctorPatientMap) {
        doctorPatientMapRepository.save(doctorPatientMap);
        return "patient:" + doctorPatientMap.getPatientId() + " mapped with doctor:" + doctorPatientMap.getDoctorId();
    }

    public String addHospitalAdminMap(HospitalAdminMap hospitalAdminMap) {
        hospitalAdminMapRepository.save(hospitalAdminMap);
        return "hospital:" + hospitalAdminMap.getHospitalId() + " mapped with admin:" + hospitalAdminMap.getAdminId();
    }

    public String addHospitalDoctorMap(HospitalDoctorMap hospitalDoctorMap) {
        hospitalDoctorMapRepository.save(hospitalDoctorMap);
        return "doctor:" + hospitalDoctorMap.getDoctorId() + "mapped with hospital:" + hospitalDoctorMap.getHospitalId();
    }
    public String deleteHospitalAdminMap(Long id) {
        hospitalAdminMapRepository.deleteById(id);
        return "id " + id + " map deleted with admin";
    }


}
