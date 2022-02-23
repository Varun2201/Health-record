package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.Hospital;
import com.teamVoid.healthRecord.service.HospitalService;
import com.teamVoid.healthRecord.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class HospitalController {

    private final HospitalService hospitalService;
    private final MappingService mappingService;

    @Autowired
    public HospitalController(HospitalService hospitalService, MappingService mappingService) {
        this.hospitalService = hospitalService;
        this.mappingService = mappingService;
    }

    @GetMapping(path = "get/hospitals")
    public List<Hospital> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PostMapping(path = "add/hospital")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN')")
    public String addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @PutMapping(path = "update/hospital/{id}")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN')")
    public String updateHospital(@PathVariable String id, @RequestBody Hospital hospital) {
        String hospAdmin = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority i:grantedAuthorities) authorities.add(i.getAuthority());
        if (mappingService.checkAdminHospital(hospAdmin, id) || authorities.contains("ROOT_ADMIN"))
            return hospitalService.updateHospital(id, hospital);
        else throw new IllegalArgumentException("hospital admin not mapped with hospital");
    }
    @GetMapping(path = "get/hospital/{id}")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN','HOSP_ADMIN')")
    public Hospital getHospitalById(@PathVariable String id) {
        return hospitalService.getHospital(id);
    }

    @DeleteMapping(path = "delete/hospital/{id}")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN')")
    public String deleteHospital(@PathVariable String id) {
        return hospitalService.deleteHospital(id);
    }
}
