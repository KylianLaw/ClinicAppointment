package com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer;


import com.adeleke.clinicappointment.AppointmentFollowUpPlan.BusinessLayer.AppointmentFollowUpPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/follow-up-plans")
public class AppointmentFollowUpPlanController {

    private final AppointmentFollowUpPlanService appointmentFollowUpPlanService;
    private final AppointmentFollowUpPlanAssembler appointmentFollowUpPlanAssembler;

    @Autowired
    public AppointmentFollowUpPlanController(
            AppointmentFollowUpPlanService appointmentFollowUpPlanService,
            AppointmentFollowUpPlanAssembler appointmentFollowUpPlanAssembler
    ) {
        this.appointmentFollowUpPlanService = appointmentFollowUpPlanService;
        this.appointmentFollowUpPlanAssembler = appointmentFollowUpPlanAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<AppointmentFollowUpPlanResponseModel>> createFollowUpPlan(
            @RequestBody AppointmentFollowUpPlanRequestModel request
    ) {
        AppointmentFollowUpPlanResponseModel createdFollowUpPlan =
                appointmentFollowUpPlanService.createFollowUpPlan(request);

        return new ResponseEntity<>(
                appointmentFollowUpPlanAssembler.toModel(createdFollowUpPlan),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{followUpPlanId}")
    public ResponseEntity<EntityModel<AppointmentFollowUpPlanResponseModel>> getFollowUpPlan(
            @PathVariable String followUpPlanId
    ) {
        AppointmentFollowUpPlanResponseModel followUpPlan =
                appointmentFollowUpPlanService.getFollowUpPlanById(followUpPlanId);

        EntityModel<AppointmentFollowUpPlanResponseModel> model =
                appointmentFollowUpPlanAssembler.toModel(followUpPlan);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<AppointmentFollowUpPlanResponseModel>>> getAllFollowUpPlans() {
        List<EntityModel<AppointmentFollowUpPlanResponseModel>> followUpPlans =
                appointmentFollowUpPlanService.getAllFollowUpPlans()
                        .stream()
                        .map(appointmentFollowUpPlanAssembler::toModel)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(followUpPlans, HttpStatus.OK);
    }

    @PutMapping("/{followUpPlanId}")
    public ResponseEntity<EntityModel<AppointmentFollowUpPlanResponseModel>> updateFollowUpPlan(
            @PathVariable String followUpPlanId,
            @RequestBody AppointmentFollowUpPlanRequestModel requestModel
    ) {
        AppointmentFollowUpPlanResponseModel updatedFollowUpPlan =
                appointmentFollowUpPlanService.updateFollowUpPlan(followUpPlanId, requestModel);

        return new ResponseEntity<>(
                appointmentFollowUpPlanAssembler.toModel(updatedFollowUpPlan),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{followUpPlanId}")
    public ResponseEntity<Void> deleteFollowUpPlan(
            @PathVariable String followUpPlanId
    ) {
        appointmentFollowUpPlanService.deleteFollowUpPlan(followUpPlanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{followUpPlanId}/cancel")
    public ResponseEntity<EntityModel<AppointmentFollowUpPlanResponseModel>> cancelFollowUpPlan(
            @PathVariable String followUpPlanId
    ) {
        AppointmentFollowUpPlanResponseModel cancelledFollowUpPlan =
                appointmentFollowUpPlanService.cancelFollowUpPlan(followUpPlanId);

        return new ResponseEntity<>(
                appointmentFollowUpPlanAssembler.toModel(cancelledFollowUpPlan),
                HttpStatus.OK
        );
    }

    @PutMapping("/{followUpPlanId}/complete")
    public ResponseEntity<EntityModel<AppointmentFollowUpPlanResponseModel>> completeFollowUpPlan(
            @PathVariable String followUpPlanId
    ) {
        AppointmentFollowUpPlanResponseModel completedFollowUpPlan =
                appointmentFollowUpPlanService.completeFollowUpPlan(followUpPlanId);

        return new ResponseEntity<>(
                appointmentFollowUpPlanAssembler.toModel(completedFollowUpPlan),
                HttpStatus.OK
        );
    }
}