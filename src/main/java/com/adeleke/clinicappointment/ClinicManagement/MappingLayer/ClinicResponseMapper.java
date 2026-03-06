package com.adeleke.clinicappointment.ClinicManagement.MappingLayer;

import com.adeleke.clinicappointment.ClinicManagement.DataLayer.Clinic;

import com.adeleke.clinicappointment.ClinicManagement.PresentationLayer.ClinicResponseModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicResponseMapper {

    @Mappings({
            @Mapping(source = "clinicId.clinicId", target = "clinicId"),
            @Mapping(source = "clinicName", target = "clinicName"),
            @Mapping(source = "clinicAddress", target = "clinicAddress")
    })
    ClinicResponseModel toClinicResponse(Clinic clinic);

    List<ClinicResponseModel> entityListToResponseModelList(List<Clinic> clinics);
}