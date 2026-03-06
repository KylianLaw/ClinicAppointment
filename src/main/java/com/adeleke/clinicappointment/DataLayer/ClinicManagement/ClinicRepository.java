package com.adeleke.clinicappointment.DataLayer.ClinicManagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Clinic findByClinicId_ClinicId(String clinicId);
}
