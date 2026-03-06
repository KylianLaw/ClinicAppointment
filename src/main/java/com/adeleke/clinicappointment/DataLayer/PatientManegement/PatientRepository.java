package com.adeleke.clinicappointment.DataLayer.PatientManegement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPatientId(String patientId);
}
