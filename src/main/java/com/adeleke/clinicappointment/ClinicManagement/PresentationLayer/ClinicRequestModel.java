package com.adeleke.clinicappointment.ClinicManagement.PresentationLayer;

import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRequestModel {
    String clinicName;
    ClinicAddress clinicAddress;
}
