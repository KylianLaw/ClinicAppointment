package com.adeleke.clinicappointment.ClinicManagement.DataLayer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Embedded
    private ClinicIdentifier clinicId;

    @Column(name = "clinic_name")
    private String clinicName;

    @Embedded
    private ClinicAddress clinicAddress;

    public Clinic(ClinicIdentifier clinicId, String clinicName, ClinicAddress clinicAddress) {

        if(clinicId == null) throw new NullPointerException("clinicId cannot be null");
        if(clinicName == null) throw new NullPointerException("clinicName cannot be null");
        if(clinicAddress == null) throw new NullPointerException("clinicAddress cannot be null");
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;

    }
}
