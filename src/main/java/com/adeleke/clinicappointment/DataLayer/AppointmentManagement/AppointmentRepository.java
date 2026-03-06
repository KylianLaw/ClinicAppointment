package com.adeleke.clinicappointment.DataLayer.AppointmentManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Optional<Appointment> findByAppointmentId_AppointmentId(String appointmentId);

    List<Appointment> findByPatientIdentifier_PatientId(String patientId);

    List<Appointment> findByDoctorIdentifier_DoctorId(String doctorId);

    List<Appointment> findByClinicIdentifier_ClinicId(String clinicId);
}
