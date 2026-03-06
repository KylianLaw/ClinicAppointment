package com.adeleke.clinicappointment.DoctorManagement.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class DoctorIdentifier {
    @Column(name = "doctor_id", nullable = false)
    private String doctorId;

    public DoctorIdentifier() {
        this.doctorId = "Doctor-" + java.util.UUID.randomUUID().toString();
    }

    public DoctorIdentifier(String doctorId) {this.doctorId = doctorId;}

}
