package com.adeleke.clinicappointment.PatientManagement.PresentationLayer;


import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientAddress;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientContactInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestModel {
    String patientFullName;
    Date birthDate;
    PatientAddress addressRequest;
    PatientContactInfo contactInfoRequest;
}
