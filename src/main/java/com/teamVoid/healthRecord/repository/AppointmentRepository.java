package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment getAppointmentById(Long id);
}
