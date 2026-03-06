package com.adeleke.clinicappointment.DoctorManagement.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class DoctorContactInfo {
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    public DoctorContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public DoctorContactInfo() {
        this.email = "Not Provided, need to update";
        this.phoneNumber = "Not Provided, need to update";
    }
}
