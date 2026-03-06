package com.adeleke.clinicappointment.DataLayer.ClinicManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ClinicIdentifier {

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    public ClinicIdentifier(String clinicId) {
        this.clinicId = clinicId;
    }

    public ClinicIdentifier() {
        this.clinicId = "Clinic-" + java.util.UUID.randomUUID().toString();
    }
}
