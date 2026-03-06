package com.adeleke.clinicappointment.PatientManagement.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPatientId_PatientId(String patientId);
}
