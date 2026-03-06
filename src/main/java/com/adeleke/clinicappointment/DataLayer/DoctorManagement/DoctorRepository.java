package com.adeleke.clinicappointment.DataLayer.DoctorManagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByDoctorId(String doctorId);
//    Doctor findAllByDoctorName(String doctorName);
//    Doctor findAllByDoctorIdAndDoctorName(String doctorId, String doctorName);
}
