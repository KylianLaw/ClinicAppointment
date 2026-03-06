package com.adeleke.clinicappointment.ClinicManagement.PresentationLayer;

import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicAddress;
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
