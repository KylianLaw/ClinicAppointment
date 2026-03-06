package com.adeleke.clinicappointment.DoctorManagement.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByDoctorId_DoctorId(String doctorId);
//    Doctor findAllByDoctorName(String doctorName);
//    Doctor findAllByDoctorIdAndDoctorName(String doctorId, String doctorName);
    Doctor findByDoctorFullNameAndSpecialty(String doctorFullName, SpecialtyEnum specialty);
}
