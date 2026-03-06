package com.adeleke.clinicappointment.ClinicManagement.MappingLayer;

import com.adeleke.clinicappointment.ClinicManagement.DataLayer.Clinic;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ClinicRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(clinicId)", target = "clinicId"),
            @Mapping(source = "requestModel.clinicName", target = "clinicName"),
            @Mapping(source = "requestModel.clinicAddress", target = "clinicAddress")
    })
    Clinic toEntity(ClinicRequestModel requestModel,
                    ClinicIdentifier clinicId);
}