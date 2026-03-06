package com.adeleke.clinicappointment.DoctorManagement.PresentationLayer;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DoctorAssembler implements RepresentationModelAssembler<DoctorResponseModel, EntityModel<DoctorResponseModel>> {

    @Override
    public EntityModel<DoctorResponseModel> toModel(DoctorResponseModel dto) {

        EntityModel<DoctorResponseModel> model = EntityModel.of(dto,
                linkTo(methodOn(DoctorController.class).getDoctorById(dto.getDoctorId())).withSelfRel(),
                linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("doctors"),
                linkTo(methodOn(DoctorController.class).updateDoctor(dto.getDoctorId(), null)).withRel("update doctor")
        );

        return model;
    }
}