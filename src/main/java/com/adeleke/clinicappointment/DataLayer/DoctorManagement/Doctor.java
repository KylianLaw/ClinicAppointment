package com.adeleke.clinicappointment.DataLayer.DoctorManagement;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer Id;

    @Embedded
    private DoctorIdentifier doctorId;

    @Column(name = "doctor_full_name")
    private String doctorFullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty")
    private SpecialtyEnum specialty;

    public Doctor (DoctorIdentifier doctorId, String doctorFullName, SpecialtyEnum specialty) {
        if(doctorId == null) throw new NullPointerException("doctorId can't be null");
        if(doctorFullName == null) throw new NullPointerException("doctor full name can't be null");
        if(specialty == null) throw new NullPointerException("specialty can't be null");
        this.doctorId = doctorId;
        this.doctorFullName = doctorFullName;
        this.specialty = specialty;

    }


}


