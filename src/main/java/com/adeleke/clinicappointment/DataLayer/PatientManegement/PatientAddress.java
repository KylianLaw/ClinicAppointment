package com.adeleke.clinicappointment.DataLayer.PatientManegement;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class PatientAddress {
    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    public PatientAddress(String street, String city, String postalCode ) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public PatientAddress() {
        this.street = "Not Provided, need to update";
        this.city = "Not Provided, need to update";
        this.postalCode = "Not Provided, need to update";
    }
    ////////////////////////////////
    // Dont forget to do the validation
    ////////////////////////////////



}
