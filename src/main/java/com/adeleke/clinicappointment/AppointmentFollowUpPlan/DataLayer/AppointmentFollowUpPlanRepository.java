package com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentFollowUpPlanRepository extends JpaRepository<AppointmentFollowUpPlan, Integer> {

    Optional<AppointmentFollowUpPlan> findByFollowUpPlanId_FollowUpPlanId(String followUpPlanId);

    boolean existsByFollowUpPlanId_FollowUpPlanId(String followUpPlanId);

    void deleteByFollowUpPlanId_FollowUpPlanId(String followUpPlanId);
}