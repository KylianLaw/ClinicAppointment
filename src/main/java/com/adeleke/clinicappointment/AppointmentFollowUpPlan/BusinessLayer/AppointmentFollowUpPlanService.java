package com.adeleke.clinicappointment.AppointmentFollowUpPlan.BusinessLayer;

import com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer.AppointmentFollowUpPlanRequestModel;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer.AppointmentFollowUpPlanResponseModel;

import java.util.List;

public interface AppointmentFollowUpPlanService {

    List<AppointmentFollowUpPlanResponseModel> getAllFollowUpPlans();

    AppointmentFollowUpPlanResponseModel getFollowUpPlanById(String followUpPlanId);

    AppointmentFollowUpPlanResponseModel createFollowUpPlan(AppointmentFollowUpPlanRequestModel requestModel);

    AppointmentFollowUpPlanResponseModel updateFollowUpPlan(String followUpPlanId,
                                                            AppointmentFollowUpPlanRequestModel requestModel);

    void deleteFollowUpPlan(String followUpPlanId);

    AppointmentFollowUpPlanResponseModel completeFollowUpPlan(String followUpPlanId);

    AppointmentFollowUpPlanResponseModel cancelFollowUpPlan(String followUpPlanId);
}