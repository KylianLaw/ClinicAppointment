package com.adeleke.clinicappointment.PresentationLayer.ClinicManagement;

import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponseModel extends RepresentationModel<ClinicResponseModel> {
    String clinicId;
    String clinicName;
    ClinicAddress clinicAddress;
}
