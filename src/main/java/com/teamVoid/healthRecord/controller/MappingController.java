package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.DoctorPatientMap;
import com.teamVoid.healthRecord.model.HospitalAdminMap;
import com.teamVoid.healthRecord.model.HospitalDoctorMap;
import com.teamVoid.healthRecord.service.MappingService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private final MappingService mappingService;

    public MappingController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @PostMapping(path = "add/map/doctor_patient")
    public String addDoctorPatientMap(@RequestBody DoctorPatientMap doctorPatientMap) {
        return mappingService.addDoctorPatientMap(doctorPatientMap);
    }

    @PostMapping(path = "add/map/hosp_admin")
    public String addHospitalAdminMap(@RequestBody HospitalAdminMap hospitalAdminMap) {
        return mappingService.addHospitalAdminMap(hospitalAdminMap);
    }

    @PostMapping(path = "add/map/hosp_doctor")
    public String addHospitalDoctorMap(@RequestBody HospitalDoctorMap hospitalDoctorMap) {
        return mappingService.addHospitalDoctorMap(hospitalDoctorMap);
    }

    @DeleteMapping(path = "delete/map/hosp/admin/{id}")
    public String deleteHospitalAdminMap(@PathVariable Long id) {
        return mappingService.deleteHospitalAdminMap(id);
    }
}
