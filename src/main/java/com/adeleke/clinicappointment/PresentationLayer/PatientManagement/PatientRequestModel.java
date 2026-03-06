package com.adeleke.clinicappointment.PresentationLayer.PatientManagement;


import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientAddress;
import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientContactInfo;
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
