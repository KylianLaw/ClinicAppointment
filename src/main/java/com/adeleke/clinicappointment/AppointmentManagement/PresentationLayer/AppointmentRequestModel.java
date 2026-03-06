package com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer;


import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestModel {
    // Doctor management subdomain
    String doctorFullName;

    String Speciality;

    //Patient management subdomain

    String patientFullName;


    LocalDateTime localDateTime;


    AppointmentStatusEnum appointmentStatus;
}
