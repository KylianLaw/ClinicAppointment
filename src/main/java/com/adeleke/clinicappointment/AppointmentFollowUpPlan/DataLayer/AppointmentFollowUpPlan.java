package com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer;

import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentIdentifier;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.Exception.exceptions.InvalidFollowUpPlanStatusTransitionException;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "appointment_follow_up_plan")
public class AppointmentFollowUpPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Embedded
    private AppointmentFollowUpPlanIdentifier followUpPlanId;

    @Embedded
    private PatientIdentifier patientId;
    @Embedded
    private AppointmentIdentifier appointmentId;
    @Embedded
    private DoctorIdentifier doctorId;
    @Embedded
    private ClinicIdentifier clinicId;
    @Column(name = "follow_up_reason")
    private String followUpReason;

    @Column(name = "recommended_follow_up_date")
    private LocalDateTime recommendedFollowUpDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FollowUpPlanStatusEnum status;


    public AppointmentFollowUpPlan(AppointmentFollowUpPlanIdentifier followUpPlanId, PatientIdentifier patientId, AppointmentIdentifier appointmentId, DoctorIdentifier doctorId, ClinicIdentifier clinicId, String followUpReason, LocalDateTime recommendedFollowUpDate, FollowUpPlanStatusEnum status) {
        this.followUpPlanId = followUpPlanId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.followUpReason = followUpReason;
        this.recommendedFollowUpDate = recommendedFollowUpDate;
        this.status = status;
    }


    public void updateFollowUpDetails(String followUpReason, LocalDateTime recommendedFollowUpDate) {
        this.followUpReason = followUpReason;
        this.recommendedFollowUpDate = recommendedFollowUpDate;
    }

    public void markAsCompleted() {
        if (this.status == FollowUpPlanStatusEnum.CANCELLED) {
            throw new InvalidFollowUpPlanStatusTransitionException("A cancelled follow-up plan cannot become completed.");
        }
        this.status = FollowUpPlanStatusEnum.COMPLETED;
    }

    public void cancelPlan() {
        this.status = FollowUpPlanStatusEnum.CANCELLED;
    }

    public void changeStatus(FollowUpPlanStatusEnum newStatus) {
        if (this.status == FollowUpPlanStatusEnum.COMPLETED && newStatus == FollowUpPlanStatusEnum.PLANNED) {
            throw new InvalidFollowUpPlanStatusTransitionException("A completed follow-up plan cannot become planned again.");
        }

        if (this.status == FollowUpPlanStatusEnum.CANCELLED && newStatus == FollowUpPlanStatusEnum.COMPLETED) {
            throw new InvalidFollowUpPlanStatusTransitionException("A cancelled follow-up plan cannot become completed.");
        }

        this.status = newStatus;
    }
}

