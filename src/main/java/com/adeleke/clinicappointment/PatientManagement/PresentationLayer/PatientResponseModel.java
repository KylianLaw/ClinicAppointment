package com.adeleke.clinicappointment.PatientManagement.PresentationLayer;

import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientAddress;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientContactInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseModel extends RepresentationModel<PatientResponseModel> {
    String patientId;
    String patientFullName;
    Date birthDate;

    PatientAddress addressResponse;
    PatientContactInfo contactInfoResponse;
}




