package com.adeleke.clinicappointment.ClinicManagement.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Clinic findByClinicId_ClinicId(String clinicId);

    Clinic findByClinicNameAndClinicAddress_StreetAndClinicAddress_CityAndClinicAddress_PostalCode(String clinicName, String street, String city, String postalCode);
}
