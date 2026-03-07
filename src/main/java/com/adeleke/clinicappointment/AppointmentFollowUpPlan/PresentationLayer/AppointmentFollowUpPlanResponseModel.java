package com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer;

import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.FollowUpPlanStatusEnum;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentStatusEnum;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicAddress;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.SpecialtyEnum;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientContactInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentFollowUpPlanResponseModel extends RepresentationModel<AppointmentFollowUpPlanResponseModel> {

    // FollowUpPlan information
    private String followUpPlanId;
    private LocalDateTime recommendedFollowUpDate;
    private String followUpReason;
    private FollowUpPlanStatusEnum status;

    // Appointment information
    private String appointmentId;
    private LocalDateTime appointmentDateTime;
    private AppointmentStatusEnum appointmentStatus;

    // Patient information
    private String patientId;
    private String patientFullName;
    private PatientContactInfo patientContactInfo;

    // Doctor information
    private String doctorId;
    private String doctorFullName;
    private SpecialtyEnum specialty;

    // Clinic information
    private String clinicId;
    private String clinicName;
    private ClinicAddress clinicAddress;
}