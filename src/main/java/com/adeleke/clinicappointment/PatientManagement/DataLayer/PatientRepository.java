package com.adeleke.clinicappointment.PatientManagement.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPatientId_PatientId(String patientId);
    Patient findByPatientFullNameAndBirthDate(String patientFullName, Date birthDate);
           // findByPatientFullNameAndBirthDate for custom exception
}
