package com.adeleke.clinicappointment.DoctorManagement.PresentationLayer;


import com.adeleke.clinicappointment.DoctorManagement.DataLayer.SpecialtyEnum;
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
    //possible error, if it breaks go back to string
    SpecialtyEnum specialty;
}
