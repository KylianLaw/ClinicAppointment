package com.adeleke.clinicappointment.ClinicManagement.PresentationLayer;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClinicAssembler implements RepresentationModelAssembler<ClinicResponseModel, EntityModel<ClinicResponseModel>> {

    @Override
    public EntityModel<ClinicResponseModel> toModel(ClinicResponseModel dto) {

        EntityModel<ClinicResponseModel> model = EntityModel.of(dto,
                linkTo(methodOn(ClinicController.class).getClinic(dto.getClinicId())).withSelfRel(),
                linkTo(methodOn(ClinicController.class).getAllClinics()).withRel("clinics"),
                linkTo(methodOn(ClinicController.class).updateClinic(dto.getClinicId(), null)).withRel("update clinic")
        );

        return model;
    }
}