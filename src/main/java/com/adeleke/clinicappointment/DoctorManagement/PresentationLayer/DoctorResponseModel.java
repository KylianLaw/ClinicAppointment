package com.adeleke.clinicappointment.DoctorManagement.PresentationLayer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseModel extends RepresentationModel<DoctorResponseModel> {

    String doctorId;
    String doctorFullName;
    String specialty;
}
