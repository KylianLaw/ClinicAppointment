package com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer;

import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseModel extends RepresentationModel<AppointmentResponseModel> {

    String appointmentId;

    // Doctor management subdomain
    String doctorFullName;

    String Speciality;

    //Patient management subdomain

    String patientFullName;


    LocalDateTime localDateTime;


    AppointmentStatusEnum appointmentStatus;


}
