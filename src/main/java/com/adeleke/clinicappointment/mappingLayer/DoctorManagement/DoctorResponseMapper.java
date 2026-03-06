package com.adeleke.clinicappointment.mappingLayer.DoctorManagement;

import com.adeleke.clinicappointment.DataLayer.DoctorManagement.Doctor;
import com.adeleke.clinicappointment.DataLayer.DoctorManagement.DoctorIdentifier;
import com.adeleke.clinicappointment.DataLayer.DoctorManagement.SpecialtyEnum;
import com.adeleke.clinicappointment.PresentationLayer.DoctorManagement.DoctorResponseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorResponseMapper {

    @Mappings({
            @Mapping(expression = "java(doctor.getDoctorId().getDoctorId()", target = "doctorId"),
            @Mapping(source = "doctorFullName", target = "doctorFullName"),
            @Mapping(source = "specialty", target = "specialty", qualifiedByName = "specialtyEnumToString")
    })
    DoctorResponseModel toDoctorResponse(Doctor doctor);

    List<DoctorResponseModel> entityListToResponseModelList(List<Doctor> doctors);

    @Named("specialtyEnumToString")
    default String specialtyEnumToString(Enum<?> specialty) {
        return specialty == null ? null : specialty.name();
    }
}