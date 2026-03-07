package com.adeleke.clinicappointment.AppointmentManagement.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {


    Optional<Appointment> findByAppointmentId_AppointmentId(String appointmentId);

    List<Appointment> findByPatientIdentifier_PatientId(String patientId);

    List<Appointment> findByDoctorIdentifier_DoctorId(String doctorI);

    List<Appointment> findByClinicIdentifier_ClinicId(String clinicId);

    boolean existsByPatientIdentifier_PatientIdAndAppointmentDateTime(String PatientId, LocalDateTime appointmentDateTime);
}
