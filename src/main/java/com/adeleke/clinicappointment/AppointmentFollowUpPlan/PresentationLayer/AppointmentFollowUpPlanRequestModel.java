package com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentFollowUpPlanRequestModel {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String clinicId;

    private LocalDateTime recommendedFollowUpDate;
    private String followUpReason;
}