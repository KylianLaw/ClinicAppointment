package com.adeleke.clinicappointment.AppointmentFollowUpPlan.MappingLayer;

import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.AppointmentFollowUpPlan;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.DataLayer.AppointmentFollowUpPlanIdentifier;
import com.adeleke.clinicappointment.AppointmentFollowUpPlan.PresentationLayer.AppointmentFollowUpPlanRequestModel;
import com.adeleke.clinicappointment.AppointmentManagement.DataLayer.AppointmentIdentifier;
import com.adeleke.clinicappointment.ClinicManagement.DataLayer.ClinicIdentifier;
import com.adeleke.clinicappointment.DoctorManagement.DataLayer.DoctorIdentifier;
import com.adeleke.clinicappointment.PatientManagement.DataLayer.PatientIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentFollowUpPlanRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(expression = "java(followUpPlanIdentifier)", target = "followUpPlanId"),
            @Mapping(expression = "java(patientIdentifier)", target = "patientId"),
            @Mapping(expression = "java(appointmentIdentifier)", target = "appointmentId"),
            @Mapping(expression = "java(doctorIdentifier)", target = "doctorId"),
            @Mapping(expression = "java(clinicIdentifier)", target = "clinicId"),

            @Mapping(source = "requestModel.followUpReason", target = "followUpReason"),
            @Mapping(source = "requestModel.recommendedFollowUpDate", target = "recommendedFollowUpDate"),

            @Mapping(target = "status", ignore = true)
    })
    AppointmentFollowUpPlan toAppointmentFollowUpPlan(
            AppointmentFollowUpPlanRequestModel requestModel,
            AppointmentFollowUpPlanIdentifier followUpPlanIdentifier,
            PatientIdentifier patientIdentifier,
            AppointmentIdentifier appointmentIdentifier,
            DoctorIdentifier doctorIdentifier,
            ClinicIdentifier clinicIdentifier
    );
}