package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.Appointment;
import com.teamVoid.healthRecord.service.AppointmentService;
import com.teamVoid.healthRecord.service.MappingService;
import com.teamVoid.healthRecord.service.PatientCaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final MappingService mappingService;
    private final PatientCaseService patientCaseService;

    public AppointmentController(AppointmentService appointmentService, MappingService mappingService, PatientCaseService patientCaseService) {
        this.appointmentService = appointmentService;
        this.mappingService = mappingService;
        this.patientCaseService = patientCaseService;
    }

    @PostMapping(path = "add/{doctor}/appointment")
    @PreAuthorize("authentication.principal == #doctor && hasAnyAuthority('DOCTOR')")
    public String addAppointment(@PathVariable String doctor, @RequestBody Appointment appointment) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (mappingService.checkPatientDoctor(principal, patientCaseService.getPatientCase(appointment.getPatientCase()).getUsername())
            && mappingService.checkHospitalDoctorMap(appointment.getHospital(), principal)
        )
            return appointmentService.addAppointment(appointment, doctor);
        else throw new IllegalArgumentException("doctor not mapped with patient");
    }

    @PutMapping(path = "update/{caseId}/{appId}/report")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public String updateReport(@PathVariable("caseId") Long caseId, @PathVariable("appId") Long appId, @RequestParam("report") MultipartFile file) throws IOException {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (mappingService.checkPatientDoctor(principal, patientCaseService.getPatientCase(caseId).getUsername()))
            return appointmentService.updateReport(appId, file);
        else throw new IllegalArgumentException("doctor not mapped with patient");
    }

    @DeleteMapping(path = "delete/appointment/{id}")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public String deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id);
    }
}