package com.adeleke.clinicappointment.PatientManagement.MappingLayer;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.Patient;

import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import com.adeleke.clinicappointment.PatientManagement.PresentationLayer.PatientRequestModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface PatientRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(patientId)", target = "patientId"),
            @Mapping(source = "requestModel.patientFullName", target = "patientFullName"),
            @Mapping(source = "requestModel.birthDate", target = "birthDate"),
            @Mapping(source = "requestModel.addressRequest", target = "patientAddress"),
            @Mapping(source = "requestModel.contactInfoRequest", target = "patientContactInfo")

    })
    Patient toEntity(PatientRequestModel requestModel,
                     PatientIdentifier patientId);
}
