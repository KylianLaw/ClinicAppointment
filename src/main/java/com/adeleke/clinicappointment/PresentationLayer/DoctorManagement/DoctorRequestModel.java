package com.adeleke.clinicappointment.PresentationLayer.DoctorManagement;


import com.adeleke.clinicappointment.DataLayer.DoctorManagement.SpecialtyEnum;
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
