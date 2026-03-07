package com.adeleke.clinicappointment.AppointmentManagement.MappingLayer;

import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.Appointment;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentIdentifier;
import com.adeleke.clinicappointment.AppointmentManagement.PresentationLayer.AppointmentRequestModel;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface AppointmentRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(expression = "java(appointmentIdentifier)", target = "appointmentId"),
            @Mapping(expression = "java(doctorIdentifier)", target = "doctorIdentifier"),
            @Mapping(expression = "java(patientIdentifier)", target = "patientIdentifier"),
            @Mapping(expression = "java(clinicIdentifier)", target = "clinicIdentifier"),

            @Mapping(source = "appointmentRequestModel.appointmentDateTime", target = "appointmentDateTime"),

            @Mapping(target = "appointmentStatus", ignore = true)
    })
    Appointment toAppointment(
            AppointmentRequestModel appointmentRequestModel,
            AppointmentIdentifier appointmentIdentifier,
            DoctorIdentifier doctorIdentifier,
            PatientIdentifier patientIdentifier,
            ClinicIdentifier clinicIdentifier
    );
}