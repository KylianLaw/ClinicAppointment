package com.adeleke.clinicappointment.DataLayer.PatientManegement;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PatientIdentifier {
    @Column(name = "patient_id", nullable = false)
    private String patientId;

    public PatientIdentifier() {
        this.patientId = "Patient-" + java.util.UUID.randomUUID().toString();
    }

    public PatientIdentifier(String patientId) {
        this.patientId = patientId;
    }

}
