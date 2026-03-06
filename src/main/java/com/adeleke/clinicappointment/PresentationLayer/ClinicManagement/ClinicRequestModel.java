package com.adeleke.clinicappointment.PresentationLayer.ClinicManagement;

import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRequestModel {
    String clinicName;
    ClinicAddress clinicAddressRequest;
}
