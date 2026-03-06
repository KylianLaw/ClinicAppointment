package com.adeleke.clinicappointment.PatientManagement.PresentationLayer;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PatientAssembler implements RepresentationModelAssembler<PatientResponseModel, EntityModel<PatientResponseModel>> {
    @Override
    public EntityModel<PatientResponseModel> toModel(PatientResponseModel dto) {

        EntityModel<PatientResponseModel> model = EntityModel.of(dto,
                linkTo(methodOn(PatientController.class).getPatientById(dto.getPatientId())).withSelfRel(),
                linkTo(methodOn(PatientController.class).getAllPatients()).withRel("patients"),
                linkTo(methodOn(PatientController.class).createPatient(null)).withRel("create patient"),
                linkTo(methodOn(PatientController.class).updatePatient(dto.getPatientId(), null)).withRel("update patient"),
                linkTo(methodOn(PatientController.class).deletePatient(dto.getPatientId())).withRel("delete patient")
        );
        return model;
    }
}
