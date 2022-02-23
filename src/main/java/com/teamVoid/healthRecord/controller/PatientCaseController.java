package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.PatientCase;
import com.teamVoid.healthRecord.service.MappingService;
import com.teamVoid.healthRecord.service.PatientCaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientCaseController {

    private final PatientCaseService patientCaseService;
    private final MappingService mappingService;

    public PatientCaseController(PatientCaseService patientCaseService, MappingService mappingService) {
        this.patientCaseService = patientCaseService;
        this.mappingService = mappingService;
    }

    @GetMapping(path = "get/cases")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public List<PatientCase> getPatientCases() {
        return patientCaseService.getPatientCases();
    }

    @GetMapping(path = "get/{username}/cases")
    @PreAuthorize("authentication.principal == #username || hasAnyAuthority('DOCTOR')")
    public List<PatientCase> getPatientCasesByUsername(@PathVariable String username) {
        return patientCaseService.getPatientCasesByUsername(username);
    }

    @GetMapping(path = "get/case/{username}/{id}")
    @PreAuthorize("authentication.principal == #username || hasAnyAuthority('DOCTOR')")
    public PatientCase getPatientCase(@PathVariable("username") String username, @PathVariable("id") Long id) {
        return patientCaseService.getPatientCase(username, id);
    }

    @PostMapping(path = "add/case")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public String addPatientCase(@RequestBody PatientCase patientCase) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (mappingService.checkPatientDoctor(principal, patientCase.getUsername()))
            return patientCaseService.addPatientCase(patientCase);
        else throw new IllegalArgumentException("doctor not mapped with patient");
    }
//
//    @DeleteMapping(path = "delete/case/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    public String deletePatientCase(@PathVariable Long id) {
//        return patientCaseService.deletePatientCase(id);
//    }
}
