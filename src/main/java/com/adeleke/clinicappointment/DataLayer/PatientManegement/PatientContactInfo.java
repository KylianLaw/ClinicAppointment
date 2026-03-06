package com.adeleke.clinicappointment.DataLayer.PatientManegement;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter

public class PatientContactInfo {
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    public PatientContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public PatientContactInfo() {
      this.email = "Not Provided, need to update";
      this.phoneNumber = "Not Provided, need to update";
    }


}


