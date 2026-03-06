package com.adeleke.clinicappointment.PatientManagement.DataLayer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Embedded
    private PatientIdentifier patientId;

    @Column(name = "patient_full_name")
    private String patientFullName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Embedded
    private PatientAddress patientAddress;

    @Embedded
    private PatientContactInfo patientContactInfo;

    public Patient(PatientIdentifier patientId, String patientFullName, Date birthDate, PatientAddress patientAddress, PatientContactInfo patientContactInfo) {
        if(patientId == null) throw new NullPointerException("patientId is null");
        if(patientFullName == null) throw new IllegalArgumentException("patient full name should not be null");
        if(birthDate == null) throw new IllegalArgumentException("birthDate should not be null");
        if(patientAddress == null) throw new IllegalArgumentException("patientAddress should be provided to send information relative to the appointment");
        if(patientContactInfo == null) throw new IllegalArgumentException("patientContactInfo should be provided to send information relative to the appointment");
        this.patientId = patientId;
        this.patientFullName = patientFullName;
        this.birthDate = birthDate;
        this.patientAddress = patientAddress;
        this.patientContactInfo = patientContactInfo;
    }


}
