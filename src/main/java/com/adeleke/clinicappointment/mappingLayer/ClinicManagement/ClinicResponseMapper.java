package com.adeleke.clinicappointment.mappingLayer.ClinicManagement;

import com.adeleke.clinicappointment.DataLayer.ClinicManagement.Clinic;
import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicAddress;
import com.adeleke.clinicappointment.DataLayer.ClinicManagement.ClinicIdentifier;
import com.adeleke.clinicappointment.PresentationLayer.ClinicManagement.ClinicResponseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicResponseMapper {

    @Mappings({
            @Mapping(source = "clinicId.clinicId", target = "clinicId"),
            @Mapping(source = "clinicName", target = "clinicName"),
            @Mapping(source = "clinicAddressResponse", target = "clinicAddress")
    })
    ClinicResponseModel toClinicResponse(Clinic clinic);

    List<ClinicResponseModel> entityListToResponseModelList(List<Clinic> clinics);
}