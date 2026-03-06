package com.adeleke.clinicappointment.PresentationLayer.PatientManagement;

import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientAddress;
import com.adeleke.clinicappointment.DataLayer.PatientManegement.PatientContactInfo;
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




