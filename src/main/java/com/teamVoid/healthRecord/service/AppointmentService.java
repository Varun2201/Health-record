package com.teamVoid.healthRecord.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamVoid.healthRecord.model.Appointment;
import com.teamVoid.healthRecord.model.Log;
import com.teamVoid.healthRecord.repository.AppointmentRepository;
import com.teamVoid.healthRecord.repository.LogRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final LogRepository logRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, LogRepository logRepository) {
        this.appointmentRepository = appointmentRepository;
        this.logRepository = logRepository;
    }

    public String addAppointment(Appointment appointment, String doctor) {
        appointment.setDoctor(doctor);
        try {
            appointmentRepository.save(appointment);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("invalid case or doctor or hospital");
        }
        String log = "appointment added with id:" + appointment.getId() + " in case:" + appointment.getPatientCase() + " by doctor:" + appointment.getDoctor() + " in hospital:" + appointment.getHospital();
        logRepository.save(new Log(doctor, log));
        return log;
    }

    public String updateReport(Long id, MultipartFile report) throws IOException {
        Appointment appointment = appointmentRepository.getAppointmentById(id);
        if (appointment == null) throw new IllegalArgumentException("appointment:" + id + " not found");
        appointment.setReport(report.getBytes());
        appointmentRepository.save(appointment);
        return "report updated for appointment:" + id;
    }

    public String deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
        return "appointment:" + id + " deleted";
    }
}
