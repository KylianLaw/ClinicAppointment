package com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppointmentAssembler implements RepresentationModelAssembler<AppointmentResponseModel, EntityModel<AppointmentResponseModel>> {

    @Override
    public EntityModel<AppointmentResponseModel> toModel(AppointmentResponseModel dto) {

        EntityModel<AppointmentResponseModel> model = EntityModel.of(dto,
                linkTo(methodOn(AppointmentController.class).getAppointment(dto.getAppointmentId())).withSelfRel(),
                linkTo(methodOn(AppointmentController.class).getAllAppointments()).withRel("appointments"),
                linkTo(methodOn(AppointmentController.class).createAppointment(null)).withRel("create appointment"),
                linkTo(methodOn(AppointmentController.class).updateAppointment(dto.getAppointmentId(), null)).withRel("update appointment"),
                linkTo(methodOn(AppointmentController.class).deleteAppointment(dto.getAppointmentId())).withRel("delete appointment"),
                linkTo(methodOn(AppointmentController.class).cancelAppointment(dto.getAppointmentId())).withRel("cancel appointment"),
                linkTo(methodOn(AppointmentController.class).completeAppointment(dto.getAppointmentId())).withRel("complete appointment")
        );

        return model;
    }
}