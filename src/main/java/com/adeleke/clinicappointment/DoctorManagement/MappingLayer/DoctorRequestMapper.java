package com.adeleke.clinicappointment.DoctorManagement.MappingLayer;

import com.adeleke.clinicappointment.DoctorManagement.DataLayer.Doctor;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.SpecialtyEnum;
import com.adeleke.clinicappointment.DoctorManagement.PresentationLayer.DoctorRequestModel;
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