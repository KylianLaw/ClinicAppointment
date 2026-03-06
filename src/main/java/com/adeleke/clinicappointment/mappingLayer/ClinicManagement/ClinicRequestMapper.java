package com.adeleke.clinicappointment.mappingLayer.ClinicManagement;

import com.adeleke.clinicappointment.DataLayer.ClinicManagement.Clinic;
import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicIdentifier;
import com.adeleke.clinicappointment.PresentationLayer.ClinicManagement.ClinicRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ClinicRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(clinicId)", target = "clinicId"),
            @Mapping(source = "requestModel.clinicName", target = "clinicName"),
            @Mapping(source = "requestModel.clinicAddress", target = "address")
    })
    Clinic toEntity(ClinicRequestModel requestModel,
                    ClinicIdentifier clinicId);
}