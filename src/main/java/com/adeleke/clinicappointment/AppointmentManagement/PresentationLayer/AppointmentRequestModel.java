package com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestModel {

    private String patientId;
    private String doctorId;
    private String clinicId;

    private LocalDateTime appointmentDateTime;

}
