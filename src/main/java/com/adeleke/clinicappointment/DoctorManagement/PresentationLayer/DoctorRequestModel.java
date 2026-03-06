package com.adeleke.clinicappointment.DoctorManagement.PresentationLayer;


import com.adeleke.clinicappointment.DoctorManagement.DataLayer.SpecialtyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestModel {
    String doctorFullName;
    SpecialtyEnum specialty;
}
