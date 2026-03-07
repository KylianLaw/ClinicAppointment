package com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppointmentFollowUpPlanAssembler implements
        RepresentationModelAssembler<AppointmentFollowUpPlanResponseModel,
                        EntityModel<AppointmentFollowUpPlanResponseModel>> {

    @Override
    public EntityModel<AppointmentFollowUpPlanResponseModel> toModel(
            AppointmentFollowUpPlanResponseModel dto
    ) {

        return EntityModel.of(dto,
                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .getFollowUpPlan(dto.getFollowUpPlanId())).withSelfRel(),

                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .getAllFollowUpPlans()).withRel("follow-up-plans"),

                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .createFollowUpPlan(null)).withRel("create follow-up plan"),

                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .updateFollowUpPlan(dto.getFollowUpPlanId(), null)).withRel("update follow-up plan"),

                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .deleteFollowUpPlan(dto.getFollowUpPlanId())).withRel("delete follow-up plan"),

                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .cancelFollowUpPlan(dto.getFollowUpPlanId())).withRel("cancel follow-up plan"),

                linkTo(methodOn(AppointmentFollowUpPlanController.class)
                        .completeFollowUpPlan(dto.getFollowUpPlanId())).withRel("complete follow-up plan")
        );
    }
}