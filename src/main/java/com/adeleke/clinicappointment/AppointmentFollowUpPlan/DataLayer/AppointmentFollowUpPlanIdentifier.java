package com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter

public class AppointmentFollowUpPlanIdentifier {

    @Column(name = "follow_up_plan_id", nullable = false, unique = true)
    private String followUpPlanId;

    public AppointmentFollowUpPlanIdentifier(String followUpPlanId) {
        this.followUpPlanId = followUpPlanId;
    }

    public AppointmentFollowUpPlanIdentifier() {
        this.followUpPlanId = "FOLLOWUP-" + java.util.UUID.randomUUID();
    }
}