package com.adeleke.clinicappointment.mappingLayer.DoctorManagement;

import com.adeleke.clinicappointment.DataLayer.DoctorManagement.Doctor;
import com.adeleke.clinicappointment.DataLayer.DoctorManagement.DoctorIdentifier;
import com.adeleke.clinicappointment.DataLayer.DoctorManagement.SpecialtyEnum;
import com.adeleke.clinicappointment.PresentationLayer.DoctorManagement.DoctorRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DoctorRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true), // ignores DB PK (Integer Id)
            @Mapping(expression = "java(doctorId)", target = "doctorId"),
            @Mapping(source = "requestModel.doctorFullName", target = "doctorFullName"),
            @Mapping(source = "requestModel.specialty", target = "specialty", qualifiedByName = "stringToSpecialtyEnum")
    })
    Doctor toEntity(DoctorRequestModel requestModel, DoctorIdentifier doctorId);

    @Named("stringToSpecialtyEnum")
    default SpecialtyEnum stringToSpecialtyEnum(String specialty) {
        if (specialty == null || specialty.isBlank()) return null;
        return SpecialtyEnum.valueOf(specialty.trim().toUpperCase());
    }
}