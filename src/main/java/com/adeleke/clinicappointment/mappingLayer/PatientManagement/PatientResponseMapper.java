package com.adeleke.clinicappointment.mappingLayer.PatientManagement;

import com.adeleke.clinicappointment.DataLayer.PatientManegement.Patient;


import com.adeleke.clinicappointment.PresentationLayer.PatientManagement.PatientResponseModel;

import org.mapstruct.*;
import java.util.List;



@Mapper(componentModel = "spring")
public interface PatientResponseMapper {

    @Mappings({
            @Mapping(
                    source = "patientId.patientId",
                    target = "patientId"
            ),
            @Mapping(
                    source = "patientFullName",
                    target = "patientFullName"
            ),
            @Mapping(
                    source = "birthDate",
                    target = "birthDate"
            ),
            @Mapping(
                    source = "patientAddress",
                    target = "addressResponse"
            ),
            @Mapping(
                    source = "patientContactInfo",
                    target = "contactInfoResponse"
            )
    })

    PatientResponseModel entityToResponseModel(Patient patient);

    List<PatientResponseModel> entityListToRespondModel(List<Patient> patients);

//
//    // FINISH THE CONTROLLER
//    @AfterMapping
//    default void selfLinks(@MappingTarget PatientResponseModel model, Patient
//            patient) {
//        Link selfLink =
//                linkTo(methodOn(PatientController.class)
//                        .getPatientById(model.getPatientId()))
//                        .withSelfRel();
//        model.add(selfLink);
//
//    }
}








