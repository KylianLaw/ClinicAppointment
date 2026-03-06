package com.adeleke.clinicappointment.ClinicManagement.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ClinicAddress {
    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    public ClinicAddress(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public ClinicAddress() {
        this.street = "Not Provided, need to update";
        this.city = "Not Provided, need to update";
        this.postalCode = "Not Provided, need to update";
    }
}
